package com.ERPMatrix.Application.Service_implement.purchases.reback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;
import com.ERPMatrix.Application.Repository.purchases.rebak.rebackPillRepos;
import com.ERPMatrix.Application.Service.purchases.reback.rebackPillServ;

@Service
@Transactional
public class rebackPillImpl implements rebackPillServ {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private rebackPillRepos rebackRepo;

	@Override
	public List<rebackPillModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<rebackPillModel> findByentrydate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public rebackPillModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public rebackPillModel findByPillid(String pillid) {
		// TODO Auto-generated method stub
		return rebackRepo.findByPillid(pillid);
	}

	@Override
	public List<rebackPillModel> findBySuplisername(String splisername) {
		// TODO Auto-generated method stub
		return rebackRepo.findBySuplisername(splisername);
	}

	@Override
	public List<rebackPillModel> findBySuplisernameAndEntrydateBetween(String suplisername, Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return rebackRepo.findBySuplisernameAndEntrydateBetween(suplisername, fDate, sDate);
	}

	@Override
	public List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername, Date date) {
		// TODO Auto-generated method stub
		return rebackRepo.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, date);
	}

	@Override
	public List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
			String suplisername, Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return rebackRepo.findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(suplisername, fDate,
				sDate);
	}

	@Override
	public List<rebackPillModel> findForMonitor(String suplisername, String datefrom, String dateto)
			throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");

		List<rebackPillModel> pills = new ArrayList<rebackPillModel>();

		/*
		 * date from == null date to == null && datefrom !==null
		 *
		 *
		 *
		 */

		if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null") && datefrom == null
				|| datefrom.equals("") || datefrom.equals(" ") || datefrom.equals("null")) {

			LOGGER.info("All Equal null");

			pills = findBySuplisername(suplisername);
			return pills;

		} else {

			if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null")) {
				datefrom = datefrom + " 00:00:00.0";
				LOGGER.info("date to  Equal null");

				pills = findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, SDF.parse(datefrom));
				return pills;

			} else {
				LOGGER.info("All are not null");

				datefrom = datefrom + " 00:00:00";
				dateto = dateto + " 23:59:59";
				LOGGER.info(datefrom);
				LOGGER.info(dateto);
				pills = findBySuplisernameAndEntrydateBetween(suplisername, SDF.parse(datefrom), SDF.parse(dateto));

				LOGGER.info("data rows : " + pills.size());
				return pills;

			}

		}

	}

	@Override
	public rebackPillModel save(rebackPillModel rebackPillModel) {
		System.out.println("rebackPillModel Save : " + rebackPillModel.getPrice());
		rebackPillModel findRe = findByPillid(rebackPillModel.getPillid());

		if (findRe == null) {
			return rebackRepo.save(rebackPillModel);

		} else {
			return findRe;

		}

	}

}
