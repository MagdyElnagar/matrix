package com.ERPMatrix.Application.Service_implement.finance.pill;

import static com.ERPMatrix.Application.Constant.statsicValues.END_TIME;
import static com.ERPMatrix.Application.Constant.statsicValues.START_TIME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Repository.finance.pill.pillRepository;
import com.ERPMatrix.Application.Service.finance.pill.pillServ;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
public class pillimpl implements pillServ {
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private pillRepository pillRepo;

	@Override
	public void delete(pill pill) {

		pillRepo.deleteById(pill.getId());

	}

	@Override
	public pill edit(pill pill, double amount, double amountAfterDis, char oper) throws HandlerException {

		double oldAmount = pill.getAmountbefordiscount();
		double oldAmountDis = pill.getAmountafterdiscont();

		double endAmount;

		double endAmountDis;

		if (oper == '+') {
			endAmount = oldAmount + amount;
			endAmountDis = oldAmountDis + amountAfterDis;

			pill.setAmountafterdiscont(endAmountDis);
			pill.setAmountbefordiscount(endAmount);

		} else {

			endAmount = oldAmount - amount;
			endAmountDis = oldAmountDis - amountAfterDis;

			pill.setAmountafterdiscont(endAmountDis);
			pill.setAmountbefordiscount(endAmount);

		}

		return pillRepo.save(pill);
	}

	@Override
	public List<pill> findAll() {
		// TODO Auto-generated method stub
		return pillRepo.findAll();
	}

	@Override
	public List<pill> findAllByEntrydateBetween(String fDate, String sDate) throws ParseException {
		LOGGER.info(fDate);
		LOGGER.info(sDate);

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd");

		fDate = fDate + " 00:00:00";
		sDate = sDate + " 23:59:59";

		LOGGER.info(fDate);
		LOGGER.info(sDate);

		// TODO Auto-generated method stub
		return pillRepo.findAllByEntrydateBetween(SDF.parse(fDate), SDF.parse(sDate));
	}
	
	@Override
	public List<pill> findByClientnameAndEntrydateBetween(String clientname,String fDate, String sDate) throws ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd");

		fDate = fDate + " 00:00:00";
		sDate = sDate + " 23:59:59";


		// TODO Auto-generated method stub
		return pillRepo.findByClientnameAndEntrydateBetween(clientname,SDF.parse(fDate), SDF.parse(sDate));
	}


	@Override
	public List<pill> findByClientname(String clientname) {
		if (clientname.equals(null) || clientname.equals("") || clientname.equals("null")) {
			return pillRepo.findAll();
		}

		return pillRepo.findByClientname(clientname);
	}

	@Override
	public List<pill> findByClosed(boolean colsed, String date) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (date == null || date.equals("")) {
			return pillRepo.findByClose(colsed);

		} else {

			date = date + " 00:00:00";
			return pillRepo.findByCloseAndEntrydateGreaterThanEqual(colsed, SDF.parse(date));

		}
		// TODO Auto-generated method stub
	}

	@Override
	public List<pill> findByDelegate(String delegate) {
		// TODO Auto-generated method stub
		return pillRepo.findByDelegate(delegate);
	}

	@Override
	public List<pill> findByEmployee(String employee) {
		// TODO Auto-generated method stub
		return pillRepo.findByEmployee(employee);
	}

	@Override
	public List<pill> findByEntrydateGreaterThanEqual(Date date) {
		// TODO Auto-generated method stub
		return pillRepo.findByEntrydateGreaterThanEqual(date);
	}

	@Override
	public List<pill> findByClientnameAndEntrydateGreaterThanEqual(String clientname, Date date) {
		// TODO Auto-generated method stub
		return pillRepo.findByClientnameAndEntrydateGreaterThanEqual(clientname, date);
	}

	
	@Override
	public List<pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname, String fDate,
			String sDate) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<pill> finder = new ArrayList<pill>();

		if (clientname == null || clientname.equals("") || clientname.equals("null")) {
			if (fDate.equals(null) || fDate.equals("null") || fDate.equals("") || fDate.equals(" ")) {
				finder = pillRepo.findAll();
				return finder;
			} else {
				fDate = fDate + START_TIME;

				if (sDate.equals(null) || sDate.equals("null") || sDate.equals("") || sDate.equals(" ")) {
					finder = pillRepo.findByEntrydateGreaterThanEqual(SDF.parse(fDate));
					return finder;
				} else {

					sDate = sDate + END_TIME;

					finder = pillRepo.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(SDF.parse(fDate),
							SDF.parse(sDate));
					return finder;
				}

			}

		} else {

			if (fDate.equals(null) || fDate.equals("null") || fDate.equals("") || fDate.equals(" ")) {
				finder = pillRepo.findByClientname(clientname);
				return finder;
			} else {
				fDate = fDate + START_TIME;

				if (sDate.equals(null) || sDate.equals("null") || sDate.equals("") || sDate.equals(" ")) {

					finder = pillRepo.findByClientnameAndEntrydateGreaterThanEqual(clientname, SDF.parse(fDate));
					return finder;
				} else {
					sDate = sDate + END_TIME;

					finder = pillRepo.findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(clientname,
							SDF.parse(fDate), SDF.parse(sDate));
					return finder;
				}

			}

		}

	}

	
	
	@Override
	public List<pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname, String fDate,
			String sDate) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<pill> finder = new ArrayList<pill>();

		if (clientname == null || clientname.equals("") || clientname.equals("null")) {
			if (fDate.equals(null) || fDate.equals("null") || fDate.equals("") || fDate.equals(" ")) {
				finder = pillRepo.findAll();
				return finder;
			} else {
				fDate = fDate + START_TIME;

				if (sDate.equals(null) || sDate.equals("null") || sDate.equals("") || sDate.equals(" ")) {
					finder = pillRepo.findByEntrydateGreaterThanEqual(SDF.parse(fDate));
					return finder;
				} else {

					sDate = sDate + END_TIME;

					finder = pillRepo.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(SDF.parse(fDate),
							SDF.parse(sDate));
					return finder;
				}

			}

		} else {

			if (fDate.equals(null) || fDate.equals("null") || fDate.equals("") || fDate.equals(" ")) {
				finder = pillRepo.findByClientname(clientname);
				return finder;
			} else {
				fDate = fDate + START_TIME;

				if (sDate.equals(null) || sDate.equals("null") || sDate.equals("") || sDate.equals(" ")) {

					finder = pillRepo.findByClientnameAndEntrydateGreaterThanEqual(clientname, SDF.parse(fDate));
					return finder;
				} else {
					sDate = sDate + END_TIME;

					finder = pillRepo.findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(clientname,
							SDF.parse(fDate), SDF.parse(sDate));
					return finder;
				}

			}

		}

	}

	@Override
	public pill findById(Long id) throws HandlerException {
		// TODO Auto-generated method stub

		Optional<pill> pi = pillRepo.findById(id);

		if (pi.get() == null) {
			throw new HandlerException("رقم الفاتورة غير صحيح", "pillImpl findById");

		} else {

			return pi.get();
		}

	}

	@Override
	public List<pill> findByPrint(boolean print, String date) throws ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (date == null || date.equals("")) {
			return pillRepo.findByPrint(print);

		} else {

			date = date + " 00:00:00";
			return pillRepo.findByPrintAndEntrydateGreaterThanEqual(print, SDF.parse(date));

		}
	}

	@Override
	public List<pill> findByTraficline(String traficline) {
		// TODO Auto-generated method stub
		return pillRepo.findByTraficline(traficline);
	}

	@Override
	public List<pill> findByTraficlineAndDate(String traficline, String date) throws ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (date == null || date.equals("")) {
			List<pill> pi = pillRepo.findByTraficline(traficline);
			return pi;

		} else {
			date = date + " 00:00:00";

			List<pill> pi = pillRepo.findByTraficlineAndAndEntrydateGreaterThanEqual(traficline, SDF.parse(date));
			return pi;
		}

	}

	@Override
	public pill save(pill pill) throws ParseException {

		return pillRepo.save(pill);
	}

	@Override
	public pill update(pill pill) {
		// TODO Auto-generated method stub
		return pillRepo.save(pill);
	}

}
