package com.ERPMatrix.Application.Service_implement.purchases.order;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.purchases.Order.orderdetails;
import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.Repository.purchases.order.orderdetialsRepository;
import com.ERPMatrix.Application.Service.purchases.order.orderdetailsServ;
import com.ERPMatrix.Application.Service.purchases.order.supliserorderServ;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class orderdetailsImpl implements orderdetailsServ {

	@Autowired
	private AccountatTools accountatTools;

	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private orderdetialsRepository orderdetialsRepository;

	@Autowired
	private supliserorderServ supliserorderServ;

	@Autowired
	public orderdetailsImpl(AccountatTools accountatTools, orderdetialsRepository orderdetialsRepository,
			supliserorderServ supliserorderServ) {
		super();
		this.accountatTools = accountatTools;
		this.orderdetialsRepository = orderdetialsRepository;
		this.supliserorderServ = supliserorderServ;

	}

	@Override
	public void deleteById(Long id) {
		orderdetialsRepository.deleteById(id);

	}

	@Override
	public List<orderdetails> deleteByOrderid(orderdetails orderdetails) throws HandlerException {

		supliserorder orderdata = supliserorderServ.findById(Long.decode(orderdetails.getOrderid()));

		orderdata.setTotalafterdis(0);
		orderdata.setTotalbefordis(0);

		supliserorderServ.updateOrderAmount(orderdata);

		orderdetialsRepository.deleteByOrderid(orderdetails.getOrderid());
		return null;
	}

	@Override
	public List<orderdetails> deleteRow(orderdetails orderdetails) throws HandlerException {
		supliserorder orderdata = supliserorderServ.findById(Long.decode(orderdetails.getOrderid()));
		Optional<orderdetails> row = orderdetialsRepository.findById(orderdetails.getId());

		orderdetialsRepository.deleteById(row.get().getId());
		return orderdetialsRepository.findByOrderid(orderdetails.getOrderid());
	}

	@Override
	public Optional<orderdetails> findByorderdetailsID(Long id) {

		return orderdetialsRepository.findById(id);
	}

	@Override
	public List<orderdetails> findByOrderid(String orderid) {
		return orderdetialsRepository.findByOrderid(orderid);
	}

	@Override
	public orderdetails findProductname(String productname) {
		List<orderdetails> detils = orderdetialsRepository.findByProductname(productname);
		orderdetails order = new orderdetails();
		if (detils == null || detils.size() == 0) {
			return order;
		}
		order = detils.get(0);
		int totalqouta = 0;
		for (int z = 0; z < detils.size(); z++) {
			totalqouta = totalqouta + detils.get(z).getProductqouta();
		}

		System.out.println(totalqouta);
		order.setProductqouta(totalqouta);
		return order;
	}

	@Override
	public List<orderdetails> save(orderdetails orderdetails) throws HandlerException {

		orderdetails cheack = orderdetialsRepository.findByOrderidAndProductname(orderdetails.getOrderid(),
				orderdetails.getProductname());
		supliserorder orderdata = supliserorderServ.findById(Long.decode(orderdetails.getOrderid()));
		// supliser sup = supRepo.findByName(orderdata.getSuplisername());

		double totalQoutaPrice = orderdetails.getProductprice() * orderdetails.getProductqouta();
		double priceTotalQoutaAfterDisc = totalQoutaPrice - totalQoutaPrice / 100 * orderdetails.getProductdiscount();

		double oldorderdataTotalafterisc = orderdata.getTotalafterdis();
		double oldorderdataTotalbefordis = orderdata.getTotalbefordis();
		double oldorderdetailsTotalafterdiscount = orderdetails.getTotalafterdiscount();
		double oldorderdetailsTotalbeforiscount = orderdetails.getTotalbeforiscount();

		if (cheack == null) {

			orderdata.setTotalafterdis(oldorderdataTotalafterisc + priceTotalQoutaAfterDisc);
			orderdata.setTotalbefordis(oldorderdataTotalbefordis + totalQoutaPrice);
			orderdetails.setTotalafterdiscount(oldorderdetailsTotalafterdiscount + priceTotalQoutaAfterDisc);
			orderdetails.setTotalbeforiscount(oldorderdetailsTotalbeforiscount + totalQoutaPrice);
			///////////////// plus the price from sup cridet

			// sup.setDebit(sup.getDebit() + priceTotalQoutaAfterDisc);
			supliserorder ord = new supliserorder();
			ord = orderdata;
			// supRepo.save(sup);
			orderdetialsRepository.save(orderdetails);
			supliserorderServ.updateOrderAmount(orderdata);
			return orderdetialsRepository.findByOrderid(orderdetails.getOrderid());

		} else {
			deleteById(cheack.getId());
			supliserorderServ.updateOrderAmount(orderdata);

			orderdata.setTotalafterdis(oldorderdataTotalafterisc + priceTotalQoutaAfterDisc);
			orderdata.setTotalbefordis(oldorderdataTotalbefordis + totalQoutaPrice);
			orderdetails.setTotalafterdiscount(oldorderdetailsTotalafterdiscount + priceTotalQoutaAfterDisc);
			orderdetails.setTotalbeforiscount(oldorderdetailsTotalbeforiscount + totalQoutaPrice);
			///////////////// plus the price from sup cridet

			// sup.setDebit(sup.getDebit() + priceTotalQoutaAfterDisc);
			supliserorder ord = new supliserorder();
			ord = orderdata;
			// supRepo.save(sup);
			orderdetialsRepository.save(orderdetails);
			supliserorderServ.updateOrderAmount(orderdata);
			return orderdetialsRepository.findByOrderid(orderdetails.getOrderid());
		}

	}

	@Override
	public List<orderdetails> updateRow(orderdetails orderdetails) {

		orderdetails p = orderdetialsRepository.findByOrderidAndId(orderdetails.getOrderid(), orderdetails.getId());

		supliserorder orderdata = supliserorderServ.findById(Long.decode(orderdetails.getOrderid()));

		int oldQouta = p.getProductqouta();
		double oldDiscount = p.getProductdiscount();
		double oldTotalAfterDiscount = p.getTotalafterdiscount();
		double oldTotaBeforDiscount = p.getTotalbeforiscount();

		double newpricePerone = accountatTools.getPriceAfterDiscount(orderdetails.getProductprice(),
				orderdetails.getProductdiscount());
		double newTotaAfterDisc = accountatTools.getPriceAfterDiscountForTotalQouta(orderdetails.getProductprice(),
				orderdetails.getProductdiscount(), orderdetails.getProductqouta());
		double newTotaBeforDisc = accountatTools.getPriceBedforDiscount(orderdetails.getProductprice(),
				orderdetails.getProductqouta());
		double oldOrderTAfterDis = orderdata.getTotalafterdis();
		double oldOrderTBefotDis = orderdata.getTotalbefordis();

		double minAfterDis = accountatTools.minOldAndGetNew(oldTotalAfterDiscount, newTotaAfterDisc, oldOrderTAfterDis);
		double minBeforDis = accountatTools.minOldAndGetNew(oldTotaBeforDiscount, newTotaBeforDisc, oldOrderTBefotDis);

		orderdata.setTotalafterdis(minAfterDis);
		orderdata.setTotalbefordis(minBeforDis);

		LOGGER.info("lastAfterDis : " + minAfterDis);

		LOGGER.info("lastbeforDis : " + minBeforDis);

		p.setProductBounce(orderdetails.getProductBounce());
		p.setProductdiscount(orderdetails.getProductdiscount());
		p.setProductqouta(orderdetails.getProductqouta());
		p.setTotalafterdiscount(newTotaAfterDisc);
		p.setTotalbeforiscount(newTotaBeforDisc);

		LOGGER.info("Finish");

		return orderdetialsRepository.findByOrderid(orderdetails.getOrderid());
	}

}
