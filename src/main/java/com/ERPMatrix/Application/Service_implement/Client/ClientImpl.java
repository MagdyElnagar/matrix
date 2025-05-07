package com.ERPMatrix.Application.Service_implement.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.finance.Accountat.customersafe;
import com.ERPMatrix.Application.Model.finance.Accountat.incomepayment;
import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatClientMonitoring;
import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatSaleMonitoring;
import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;
import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;
import com.ERPMatrix.Application.Repository.Client.clientRepository;
import com.ERPMatrix.Application.Service.Client.ClientServ;
import com.ERPMatrix.Application.Service.Client.customrsafeServ;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillimpl;

@Service
@Transactional
public class ClientImpl implements ClientServ {

	@Autowired
	private clientRepository clientRepo;
	@Autowired
	private customrsafeServ csServ;
	@Autowired
	private pillimpl clientPills;

	private incomepayment incomepayment;
	@Autowired
	private reback_pill_service rebackPill;

	@Override
	public client edit(client client, double amountafterdis, char oper) {

		double cird = client.getCridet();
		double debit = client.getDebit();
		System.out.println("oper : " + oper);

		if (oper == '+') {

			client.setDebit(amountafterdis + debit);
			client.setCridet(amountafterdis + cird);
			System.out.println("+");

		} else {

			client.setDebit(amountafterdis - debit);
			client.setCridet(amountafterdis - cird);
			System.out.println("-");

		}

		return clientRepo.save(client);
	}

	@Override
	public List<client> findAll() {
		// TODO Auto-generated method stub
		return clientRepo.findByStatusTrueOrderByName();
	}

	@Override
	public List<client> findAllFinanceMonitor() {
		// TODO Auto-generated method stub
		return clientRepo.findAll();
	}

	@Override
	public client findByName(String name) {
		// TODO Auto-generated method stub
		return clientRepo.findByName(name);
	}

	@Override
	public client insert(client client) {
		// TODO Auto-generated method stub
		return clientRepo.save(client);
	}

	@Override
	public client save(client client) throws HandlerException {

		if (clientRepo.findByName(client.getName()) != null) {
			throw new HandlerException("هذه العميل موجود بالفعل", "ClientImpl save");
		} else if (clientRepo.findByCommercialregister(client.getCommercialregister()) != null) {

			throw new HandlerException("السجل التجارى مسجل ل عميل اخر", "ClientImpl save");
		} else {
			client.setJoindate(new Date());
			client cli = clientRepo.save(client);
			customersafe cs = new customersafe();
			cs.setClientid(cli.getId().toString());
			cs.setClientname(cli.getName());
			customersafe cs2 = new customersafe();
			cs2 = csServ.save(cs);
			cli.setCustomrsafeid(cs2.getId().toString());

			return clientRepo.save(cli);

		}
	}

	@Override
	public AccountatClientMonitoring clientAccountMoitor(String clientname, String datefrom, String dateto)
			throws HandlerException, ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");

		AccountatClientMonitoring Monitor = new AccountatClientMonitoring();

		Monitor.setClientname(clientname);
		List<pill> pills = new ArrayList<pill>();
		List<rebackPillModel> rebackPills = new ArrayList<rebackPillModel>();
		DepitHistory depitHistory = new DepitHistory();
		if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null") && datefrom == null
				|| datefrom.equals("") || datefrom.equals(" ") || datefrom.equals("null")) {

			pills = clientPills.findByClientname(clientname);
			double depitPrice = 0;

			for (int x = 0; x < pills.size(); x++) {

				double oldAmount = Monitor.getSaleamount();
				Monitor.setSaleamount(oldAmount + pills.get(x).getAmountafterdiscont());

			//	depitPrice = depitPrice + incomepayment.findByPillIdReturnOneValue(String.valueOf(pills.get(x).getId()));

			}

			Monitor.setPayamount(depitPrice);
		//	rebackPills = rebackPill.findByclientname(clientname);
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
				pills = clientPills.findByClientnameAndEntrydateGreaterThanEqual(clientname, SDF.parse(datefrom));

				for (int x = 0; x < pills.size(); x++) {
					double oldAmount = Monitor.getSaleamount();
					Monitor.setSaleamount(oldAmount + pills.get(x).getAmountafterdiscont());

				}

				//rebackPills = rebackPill.findByclientnameAndEntrydateGreaterThanEqual(clientname, SDF.parse(datefrom));
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

				pills = clientPills.findByClientnameAndEntrydateBetween(clientname, datefrom, dateto);

				for (int x = 0; x < pills.size(); x++) {

					double oldAmount = Monitor.getSaleamount();
					Monitor.setSaleamount(oldAmount + pills.get(x).getAmountafterdiscont());

				}

				//rebackPills = rebackPill.findByclientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
					//	clientname, SDF.parse(datefrom), SDF.parse(dateto));
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

}
