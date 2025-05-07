package com.ERPMatrix.Application.Service_implement.purchases.order;

import static com.ERPMatrix.Application.Constant.statsicValues.END_TIME;
import static com.ERPMatrix.Application.Constant.statsicValues.START_TIME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERPMatrix.Application.Model.purchases.Order.orderdetails;
import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Repository.purchases.order.orderdetialsRepository;
import com.ERPMatrix.Application.Repository.purchases.order.supliserorderRepository;
import com.ERPMatrix.Application.Service.purchases.order.supliserorderServ;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpillImpl;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpilldetailsImpl;
import com.ERPMatrix.Application.Service_implement.purchases.supliser.supliserImpl;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class supliserorderImpl implements supliserorderServ {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private orderdetialsRepository orderdetialsRepository;

	private supliserorderRepository orderRepos;

	private supliserpilldetailsImpl pilldetails;
	private supliserImpl supliserImpl;
	private supliserpillImpl supliserpillImpl;

	@Autowired

	public supliserorderImpl(supliserorderRepository orderRepos, supliserpillImpl supliserpillImpl,
			supliserImpl supliserImpl, orderdetialsRepository orderdetialsRepository,
			supliserpilldetailsImpl pilldetails) {
		super();
		this.orderRepos = orderRepos;
		this.supliserpillImpl = supliserpillImpl;
		this.supliserImpl = supliserImpl;
		this.orderdetialsRepository = orderdetialsRepository;
		this.pilldetails = pilldetails;
	}

	@Override
	public void convertopill(supliserorder supliserorder) {
		// get order data
		// get order details
		// get Sup
		// ^ delete All Above after insert to sales pill

		Optional<supliserorder> orderdate = orderRepos.findById(supliserorder.getId());

		List<orderdetails> orderdetails = orderdetialsRepository.findByOrderid(String.valueOf(orderdate.get().getId()));

		supliserpill suppill = new supliserpill();

		suppill.setClosed(orderdate.get().isClosed());
		suppill.setDebittosupliser(orderdate.get().getTotalafterdis());
		suppill.setEmployeename(orderdate.get().getEmployeename());
		suppill.setEntrydate(orderdate.get().getEntrydate());
		suppill.setLastedit(orderdate.get().getLastedit());
		suppill.setEditemployeename(orderdate.get().getEditemployeename());
		suppill.setNumberofprint(0);
		suppill.setPrint(orderdate.get().isPrint());
		suppill.setStore(orderdate.get().getStore());
		suppill.setSupliserid(orderdate.get().getSupliserid());
		suppill.setSuplisername(orderdate.get().getSuplisername());
		suppill.setSupliserpillid(orderdate.get().getSupliserid());
		suppill.setTotalbefordis(orderdate.get().getTotalbefordis());
		suppill.setTotalafterdis(orderdate.get().getTotalafterdis());

		supliserpill newpill = supliserpillImpl.save(suppill);
		orderRepos.deleteById(orderdate.get().getId());

		for (int x = 0; x < orderdetails.size(); x++) {
			supliserpilldetails pilldetils = new supliserpilldetails();
			pilldetils.setDate(new Date());
			pilldetils.setPillid(String.valueOf(newpill.getId()));
			pilldetils.setProductBounce(orderdetails.get(x).getProductBounce());
			pilldetils.setProductdiscount(orderdetails.get(x).getProductdiscount());
			pilldetils.setProductname(orderdetails.get(x).getProductname());
			pilldetils.setProductprice(orderdetails.get(x).getProductprice());
			pilldetils.setProductqouta(orderdetails.get(x).getProductqouta());
			pilldetils.setTotalafterdiscount(orderdetails.get(x).getTotalafterdiscount());
			pilldetils.setTotalbeforiscount(orderdetails.get(x).getTotalbeforiscount());
			List<supliserpilldetails> pillsd = pilldetails.ConvertOrder(pilldetils);
			orderdetialsRepository.deleteById(orderdetails.get(x).getId());

		}

	}

	@Override
	public List<supliserorder> findAll() {

		return orderRepos.findAll();
	}

	@Override
	public List<supliserorder> findAllByEntrydate(Date date) {
		// TODO Auto-generated method stub
		return orderRepos.findAllByEntrydate(date);
	}

	@Override
	public List<supliserorder> findAllByEntrydateBetween(Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return orderRepos.findAllByEntrydateBetween(fDate, sDate);
	}

	@Override
	public List<supliserorder> findByClosed(boolean colsed) {
		// TODO Auto-generated method stub
		return orderRepos.findByClosed(colsed);
	}

	@Override
	public List<supliserorder> findByEmployeename(String employee) {
		// TODO Auto-generated method stub
		return orderRepos.findByEmployeename(employee);
	}

	@Override
	public List<supliserorder> findByEntrydateGreaterThanEqual(Date date) {

		return orderRepos.findByEntrydateGreaterThanEqual(date);

	}

	@Override
	public List<supliserorder> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate) {

		return orderRepos.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(fDate, sDate);
	}

	@Override
	public supliserorder findById(Long id) {
		// TODO Auto-generated method stub
		Optional<supliserorder> pill = orderRepos.findById(id);
		return updateOrderAmount(pill.get());

	}

	@Override
	public List<supliserorder> findByPrint(boolean print) {
		// TODO Auto-generated method stub
		return orderRepos.findByPrint(print);
	}

	@Override
	public List<supliserorder> findByPrintfalse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserorder> findByPrintTrue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserorder> findBySupliserid(String Supliserid) {
		// TODO Auto-generated method stub
		return orderRepos.findBySupliserid(Supliserid);
	}

	@Override
	public List<supliserorder> findBySupliseridAndSuplisername(String Supliserid, String suplisername) {
		// TODO Auto-generated method stub
		return orderRepos.findBySupliseridAndSuplisername(Supliserid, suplisername);
	}

	@Override
	public List<supliserorder> findBySuplisername(String suplisername) {
		// TODO Auto-generated method stub
		return orderRepos.findBySuplisername(suplisername);
	}

	@Override
	public supliserorder findBySupliserpillid(String supliserPillId) {
		// TODO Auto-generated method stub
		return orderRepos.findBySupliserpillid(supliserPillId);
	}

	@Override
	public List<supliserorder> findSearchOrder(String suplisername, String datefrom, String dateto)
			throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");

		List<supliserorder> order = new ArrayList();
		LOGGER.info(suplisername + "  -  " + datefrom + "  -  " + dateto);
		if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null") && datefrom == null
				|| datefrom.equals("") || datefrom.equals(" ") || datefrom.equals("null")) {

			if (suplisername == null || suplisername.equals("") || suplisername.equals(" ")
					|| suplisername.equals("null")) {
				order = orderRepos.findAll();

				return order;

			} else {

				order = orderRepos.findBySuplisername(suplisername);

				return order;
			}

		} else {

			if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null")) {

				datefrom = datefrom + START_TIME;

				if (suplisername == null || suplisername.equals("") || suplisername.equals(" ")) {
					order = orderRepos.findByEntrydateGreaterThanEqual(SDF.parse(datefrom));

					return order;

				} else {

					order = orderRepos.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername,
							SDF.parse(datefrom));

					return order;
				}

			} else {

				datefrom = datefrom + START_TIME;
				dateto = dateto + END_TIME;
				LOGGER.info("Supliser date  - from date : " + datefrom + "  -  to date : " + dateto);

				if (suplisername == null || suplisername.equals("") || suplisername.equals(" ")) {
					order = orderRepos.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(SDF.parse(datefrom),
							SDF.parse(dateto));

					return order;

				} else {

					order = orderRepos.findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
							suplisername, SDF.parse(datefrom), SDF.parse(dateto));

					return order;
				}

			}
		}
	}

	@Override
	public supliserorder save(supliserorder supliserorder) throws HandlerException {
		supliser sup = supliserImpl.findSupByName(supliserorder.getSuplisername());
		if (sup == null) {
			throw new HandlerException("اسم المورد خطأ", "supliserorderImpl save");
		} else {
			supliserorder.setSupliserid(sup.getId().toString());
			return orderRepos.save(supliserorder);
		}
	}

	@Override
	public supliserorder update(supliserorder supliserorder) {
		supliserorder.setLastedit(new Date());

		return orderRepos.save(supliserorder);
	}

	@Override
	public supliserorder updateOrderAmount(supliserorder supliserorder) {
		List<orderdetails> order = orderdetialsRepository.findByOrderid(String.valueOf(supliserorder.getId()));
		double afterDis = 0;

		double beforDis = 0;
		if (order == null) {
			supliserorder.setOrderproductnum(0);
			supliserorder.setTotalafterdis(0);
			supliserorder.setTotalbefordis(0);

		} else {

			if (order.size() > 0) {
				for (int x = 0; x < order.size(); x++) {

					afterDis = afterDis + order.get(x).getTotalafterdiscount();
					beforDis = beforDis + order.get(x).getTotalbeforiscount();

				}
				supliserorder.setOrderproductnum(order.size());
				supliserorder.setTotalafterdis(afterDis);
				supliserorder.setTotalbefordis(beforDis);

			} else {

				supliserorder.setOrderproductnum(0);
				supliserorder.setTotalafterdis(0);
				supliserorder.setTotalbefordis(0);

			}

		}
		return update(supliserorder);

	}

}
