package com.ERPMatrix.Application.Service_implement.purchases.supliser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatSaleMonitoring;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;
import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Repository.purchases.supliser.supliserRepository;
import com.ERPMatrix.Application.Service.purchases.supliser.depitHistoryServ;
import com.ERPMatrix.Application.Service.purchases.supliser.supliserServ;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpillImpl;
import com.ERPMatrix.Application.Service_implement.purchases.reback.rebackPillImpl;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service

public class supliserImpl implements supliserServ {

	@Autowired
	private depitHistoryServ depitServ;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private supliserpillImpl pillImpl;
	@Autowired
	private rebackPillImpl rebackPill;
	@Autowired
	private supliserRepository supRepo;

	@Override
	public List<supliser> findAll() {
		// TODO Auto-generated method stub
		return supRepo.findAll();
	}

	@Override
	public List<supliser> findByStatus(Boolean status) {
		return supRepo.findByStatus(status);
	}

	@Override
	public supliser findSup(supliser supliser) {
		// TODO Auto-generated method stub

		return supRepo.findByName(supliser.getName());
	}

	@Override
	public supliser findSupByName(String supliser) {
		// TODO Auto-generated method stub
		return supRepo.findByName(supliser);
	}

	@Override
	public supliser savesupliser(supliser supliser) throws HandlerException {

		supliser sup = findSupByName(supliser.getName());
		if (sup == null) {
			return supRepo.save(supliser);

		} else {

			throw new HandlerException("هذا المورد موجود بالفعل", "supliserImpl savesupliser");
		}
	}

	@Override
	public AccountatSaleMonitoring supliserAccountMoitor(String suplisername, String datefrom, String dateto)
			throws HandlerException, ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");

		AccountatSaleMonitoring Monitor = new AccountatSaleMonitoring();

		Monitor.setSuplisername(suplisername);
		List<supliserpill> pills = new ArrayList<supliserpill>();
		List<rebackPillModel> rebackPills = new ArrayList<rebackPillModel>();
		DepitHistory depitHistory = new DepitHistory();
		if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null") && datefrom == null
				|| datefrom.equals("") || datefrom.equals(" ") || datefrom.equals("null")) {

			pills = pillImpl.findBySuplisername(suplisername);
			double depitPrice = 0;

			for (int x = 0; x < pills.size(); x++) {

				double oldAmount = Monitor.getSaleamount();
				Monitor.setSaleamount(oldAmount + pills.get(x).getTotalafterdis());

				depitPrice = depitPrice + depitServ.findByPillIdReturnOneValue(String.valueOf(pills.get(x).getId()));

			}

			Monitor.setPayamount(depitPrice);
			rebackPills = rebackPill.findBySuplisername(suplisername);
			for (int x = 0; x < rebackPills.size(); x++) {
				double oldAmount = Monitor.getSalerebackamount();

				Monitor.setSalerebackamount(oldAmount + rebackPills.get(x).getPrice());

			}

			if (Monitor.getSaleamount() == 0) {
				Monitor.setDebit(Monitor.getSalerebackamount());

			} else if (Monitor.getSalerebackamount() == 0) {

				Monitor.setDebit(Monitor.getSaleamount());

			} else {
				Monitor.setDebit(Monitor.getSaleamount() - Monitor.getSalerebackamount());
			}

			return Monitor;

		} else {

			if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null")) {

				datefrom = datefrom + " 00:00:00.0";
				pills = pillImpl.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, SDF.parse(datefrom));

				for (int x = 0; x < pills.size(); x++) {
					double oldAmount = Monitor.getSaleamount();
					Monitor.setSaleamount(oldAmount + pills.get(x).getTotalafterdis());

				}

				rebackPills = rebackPill.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername,
						SDF.parse(datefrom));
				for (int x = 0; x < rebackPills.size(); x++) {
					double oldAmount = Monitor.getSalerebackamount();
					Monitor.setSalerebackamount(oldAmount + rebackPills.get(x).getPrice());

				}

				if (Monitor.getSaleamount() == 0) {
					Monitor.setDebit(Monitor.getSalerebackamount());

				} else if (Monitor.getSalerebackamount() == 0) {

					Monitor.setDebit(Monitor.getSaleamount());

				} else {
					Monitor.setDebit(Monitor.getSaleamount() - Monitor.getSalerebackamount());
				}

				return Monitor;

			} else {

				datefrom = datefrom + " 00:00:00";
				dateto = dateto + " 23:59:59";
				LOGGER.info("Supliser date  - from date : " + datefrom + "  -  to date : " + dateto);

				pills = pillImpl.findBySuplisernameAndEntrydateBetween(suplisername, SDF.parse(datefrom),
						SDF.parse(dateto));

				for (int x = 0; x < pills.size(); x++) {

					double oldAmount = Monitor.getSaleamount();
					Monitor.setSaleamount(oldAmount + pills.get(x).getTotalafterdis());

				}

				rebackPills = rebackPill.findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
						suplisername, SDF.parse(datefrom), SDF.parse(dateto));
				for (int x = 0; x < rebackPills.size(); x++) {
					double oldAmount = Monitor.getSalerebackamount();
					Monitor.setSalerebackamount(oldAmount + rebackPills.get(x).getPrice());

				}
				if (Monitor.getSaleamount() == 0) {
					Monitor.setDebit(Monitor.getSalerebackamount());

				} else if (Monitor.getSalerebackamount() == 0) {

					Monitor.setDebit(Monitor.getSaleamount());

				} else {
					Monitor.setDebit(Monitor.getSaleamount() - Monitor.getSalerebackamount());
				}

				return Monitor;

			}

		}

	}

	@Override
	public supliser updatesupliser(supliser supliser) {

		return supRepo.save(supliser);
	}

}
