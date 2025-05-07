package com.ERPMatrix.Application.Service_implement.finance.pill.reback;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;
import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill_details;
import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Repository.finance.pill.reback.reback_pill_details_repo;
import com.ERPMatrix.Application.Repository.finance.pill.reback.reback_pill_repo;
import com.ERPMatrix.Application.Repository.product.productRepository;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_details_service;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillDetailsImpl;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
public class reback_pill_details_implment implements reback_pill_details_service {

	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private pillDetailsImpl pillDetailsImpl;
	@Autowired
	private productRepository productRepository;
	@Autowired
	private reback_pill_details_repo reback_details_repo;
	@Autowired
	private reback_pill_implment reback_pill_implment;
	@Autowired
	private reback_pill_repo reback_repo;
	@Autowired
	private AccountatTools tools;

	@Override
	public List<reback_pill_details> delete(reback_pill_details reback_pill_details) {

		reback_pill_details find = findByProductnameAndRebackid(reback_pill_details.getProduct(),
				reback_pill_details.getRebackid());
		product pro = productRepository.findByName(reback_pill_details.getProduct());
		int qty = pro.getQouta() - reback_pill_details.getQty();
		pro.setQouta(qty);
		productRepository.save(pro);

		reback_details_repo.deleteById(find.getId());
		reback_pill_implment.autoUpdatePill(reback_pill_details.getRebackid());

		return findByRebackid(reback_pill_details.getRebackid());

	}

	@Override
	public reback_pill_details findById(String id) {
		// TODO Auto-generated method stub
		return reback_details_repo.findById(Long.parseLong(id)).get();
	}

	@Override
	public List<reback_pill_details> findByProductname(String productname) {
		// TODO Auto-generated method stub
		return reback_details_repo.findByProduct(productname);
	}

	@Override
	public reback_pill_details findByProductnameAndRebackid(String productname, String rebackid) {
		// TODO Auto-generated method stub
		return reback_details_repo.findByProductAndRebackid(productname, rebackid);
	}

	@Override
	public List<reback_pill_details> findByRebackid(String id) {

		// TODO Auto-generated method stub
		return reback_details_repo.findByRebackid(id);
	}

	@Override
	public List<reback_pill_details> save(reback_pill_details reback_pill_details) throws HandlerException {

		double amount = tools.getPriceAfterDiscountForTotalQouta(reback_pill_details.getPrice(),
				reback_pill_details.getDiscount(), reback_pill_details.getQty());
		reback_pill_details.setAmount(amount);
		reback_pill reback_pill = reback_pill_implment.findById(Long.parseLong(reback_pill_details.getRebackid()));
		reback_pill_details cheakRow = reback_details_repo.findByRebackidAndProduct(reback_pill_details.getRebackid(),
				reback_pill_details.getProduct());

		pilldetails finance_Pill_details = pillDetailsImpl.findByProductnameAndPillidAndPatch(
				reback_pill_details.getProduct(), reback_pill.getPillid(), reback_pill_details.getPatch());

		if (reback_pill_details.getQty() > finance_Pill_details.getQouta()) {
			throw new HandlerException("كمية المرتجع اكبر من كمية فاتورة البيع", "Save From Reback Pill Impl");
		} else {
			reback_pill_details.setDate(new Date());
			reback_pill_details.setCliname(reback_pill.getClientname());
			if (cheakRow == null) {
				product pro = productRepository.findByName(reback_pill_details.getProduct());
				int qty = pro.getQouta() + reback_pill_details.getQty();
				pro.setQouta(qty);
				productRepository.save(pro);
				reback_pill_details details = reback_details_repo.save(reback_pill_details);

			} else {
				if (cheakRow.getQty() == finance_Pill_details.getQouta()) {
					throw new HandlerException("هذا الصنف موجود بالفعل", "Save From Reback Pill Impl");

				}

				product pro = productRepository.findByName(reback_pill_details.getProduct());
				int qty = pro.getQouta() + reback_pill_details.getQty();
				pro.setQouta(qty);
				productRepository.save(pro);
				reback_details_repo.delete(cheakRow);
				reback_details_repo.save(reback_pill_details);
				reback_pill_implment.autoUpdatePill(reback_pill_details.getRebackid());

			}

			reback_pill_implment.autoUpdatePill(reback_pill_details.getRebackid());

			return reback_details_repo.findByRebackid(reback_pill_details.getRebackid());
		}
	}

	@Override
	public reback_pill_details update(reback_pill_details reback_pill_details) {
		// TODO Auto-generated method stub
		return reback_details_repo.save(reback_pill_details);
	}

}
