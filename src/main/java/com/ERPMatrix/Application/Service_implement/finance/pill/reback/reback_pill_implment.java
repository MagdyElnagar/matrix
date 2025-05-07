package com.ERPMatrix.Application.Service_implement.finance.pill.reback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;
import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill_details;
import com.ERPMatrix.Application.Repository.finance.pill.reback.reback_pill_details_repo;
import com.ERPMatrix.Application.Repository.finance.pill.reback.reback_pill_repo;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_service;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillimpl;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
public class reback_pill_implment implements reback_pill_service {

	@Autowired
	private pillimpl pillimpl;
	@Autowired
	private reback_pill_details_repo reback_details_repo;
	@Autowired
	private reback_pill_repo reback_repo;

	public void autoUpdatePill(String id) {
		reback_pill pill = reback_repo.findById(Long.parseLong(id)).get();

		List<reback_pill_details> details = reback_details_repo.findByRebackid(id);
		double pillAmount = 0;
		if (details != null) {

			for (int x = 0; x < details.size(); x++) {
				pillAmount = pillAmount + details.get(x).getAmount();
			}

			pill.setAmount(pillAmount);
		}
		update(pill);

	}

	@Override
	public void delete(reback_pill reback_pill) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<reback_pill> findAll() {
		// TODO Auto-generated method stub
		return reback_repo.findAll();
	}

	@Override
	public List<reback_pill> findByClientname(String clientname) {
		// TODO Auto-generated method stub
		return reback_repo.findByClientname(clientname);
	}

	@Override
	public List<reback_pill> findByClientnameAndEntrydateGreaterThanEqual(String clientname, String date)
			throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat FullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		date = SDF.format(date);
		date = date + " 00:00:00";

		return reback_repo.findByClientnameAndEntrydateGreaterThanEqual(clientname, FullDate.parse(date));
	}

	@Override
	public List<reback_pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname,
			String fdate, String ldate) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat FullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		fdate = SDF.format(fdate);
		ldate = SDF.format(ldate);
		fdate = fdate + " 00:00:00";
		ldate = ldate + " 23:59:59";

		return reback_repo.findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(clientname,
				FullDate.parse(fdate), FullDate.parse(ldate));
	}

	@Override
	public List<reback_pill> findByEmployeename(String employeename) {
		// TODO Auto-generated method stub
		return reback_repo.findByEmployeename(employeename);
	}

	@Override
	public List<reback_pill> findByEntrydateGreaterThanEqual(String fdate) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat FullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		fdate = SDF.format(fdate);
		fdate = fdate + " 00:00:00";

		return reback_repo.findByEntrydateGreaterThanEqual(FullDate.parse(fdate));
	}

	@Override
	public List<reback_pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String fdate, String ldate)
			throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat FullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		fdate = SDF.format(fdate);
		ldate = SDF.format(ldate);
		fdate = fdate + " 00:00:00";
		ldate = ldate + " 23:59:59";

		return reback_repo.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(FullDate.parse(ldate),
				FullDate.parse(ldate));
	}

	@Override
	public reback_pill findById(Long id) {
		// TODO Auto-generated method stub
		return reback_repo.findById(id).get();
	}

	@Override
	public reback_pill findByPillid(String pillid) {
		// TODO Auto-generated method stub
		return reback_repo.findByPillid(pillid);
	}

	@Override
	public reback_pill save(reback_pill reback_pill) throws NumberFormatException, HandlerException {
		reback_pill.setEntrydate(new Date());
		reback_pill find = findByPillid(reback_pill.getPillid());

		if (find == null) {
			reback_pill.setClientname(pillimpl.findById(Long.parseLong(reback_pill.getPillid())).getClientname());
			return reback_repo.save(reback_pill);

		} else {
			return find;

		}

	}

	@Override
	public reback_pill update(reback_pill reback_pill) {
		// TODO Auto-generated method stub
		return reback_repo.save(reback_pill);
	}

}
