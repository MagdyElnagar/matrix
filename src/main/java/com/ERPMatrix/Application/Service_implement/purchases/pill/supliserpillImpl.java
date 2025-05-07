package com.ERPMatrix.Application.Service_implement.purchases.pill;

import static com.ERPMatrix.Application.Constant.statsicValues.END_TIME;
import static com.ERPMatrix.Application.Constant.statsicValues.START_TIME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Repository.purchases.pill.supliserpillRepository;
import com.ERPMatrix.Application.Repository.purchases.pill.supliserpilldetailsRepository;
import com.ERPMatrix.Application.Repository.purchases.supliser.supliserRepository;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespillServ;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;

@Service
@Transactional
public class supliserpillImpl implements salespillServ {

	@Autowired
	private AccountatTools accTools;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private productbatchServ patchServ;
	@Autowired
	private supliserpilldetailsRepository pilldetailsRepo;
	@Autowired
	private supliserpillRepository pillRepo;
	@Autowired
	private supliserRepository supliserRepo;

	@Override
	public supliserpill changeName(Long pillid, String oldName, String newName) {
		Optional<supliserpill> pill = pillRepo.findById(pillid);

		supliser old_sup = supliserRepo.findByName(oldName);

		supliser new_sup = supliserRepo.findByName(newName);
		old_sup.setDebit(accTools.minThis(old_sup.getDebit(), pill.get().getTotalafterdis()));

		new_sup.setDebit(accTools.plusThis(new_sup.getDebit(), pill.get().getTotalafterdis()));
		pill.get().setSuplisername(newName);

		return null;

	}

	@Override
	public supliserpill changePillSupliser(supliserpill supliserpill) {

		Optional<supliserpill> old = findById(supliserpill.getId());

		if (supliserpill.getSuplisername().equals(old.get().getSuplisername())) {

			return update(supliserpill);

		} else {

			supliser newSup = supliserRepo.findByName(supliserpill.getSuplisername());
			supliser oldSup = supliserRepo.findByName(old.get().getSuplisername());

			double oldSupDebit = oldSup.getDebit() - old.get().getTotalafterdis();
			oldSup.setDebit(oldSupDebit);

			double newSupDebit = newSup.getDebit() + old.get().getTotalafterdis();
			newSup.setDebit(newSupDebit);
			old.get().setSuplisername(supliserpill.getSuplisername());
			old.get().setSupliserid(newSup.getId().toString());

			List<productbatch> patch = patchServ.findByPillid(String.valueOf(old.get().getId()));

			if (!(patch == null)) {

				for (int x = 0; x < patch.size(); x++) {
					patch.get(x).setSupliser(supliserpill.getSuplisername());
					patchServ.updatePatch(patch.get(x));
				}

			}

			supliserRepo.save(newSup);
			supliserRepo.save(oldSup);

			return update(old.get());
		}
	}

	@Override
	public void close(Long id) {
		Optional<supliserpill> pill = pillRepo.findById(id);
		pill.get().setClosed(false);
		pillRepo.save(pill.get());

	}

	@Override
	public List<supliserpill> findAll() {

		return pillRepo.findAll();
	}

	@Override
	public List<supliserpill> findAllByEntrydate(Date date) {
		// TODO Auto-generated method stub
		return pillRepo.findAllByEntrydate(date);
	}

	@Override
	public List<supliserpill> findAllByEntrydateBetween(Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return pillRepo.findAllByEntrydateBetween(fDate, sDate);
	}

	@Override
	public List<supliserpill> findByClosed(boolean colsed) {
		// TODO Auto-generated method stub
		return pillRepo.findByClosed(colsed);
	}

	@Override
	public List<supliserpill> findByEmployeename(String employee) {
		// TODO Auto-generated method stub
		return pillRepo.findByEmployeename(employee);
	}

	@Override
	public List<supliserpill> findByEntrydateGreaterThanEqual(Date date) {
		// TODO Auto-generated method stub
		return pillRepo.findByEntrydateGreaterThanEqual(date);
	}

	@Override
	public List<supliserpill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return pillRepo.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(fDate, sDate);
	}

	@Override
	public Optional<supliserpill> findById(Long id) {
		return pillRepo.findById(id);
	}

	@Override
	public List<supliserpill> findByPrint(boolean print) {
		// TODO Auto-generated method stub
		return pillRepo.findByPrint(print);
	}

	@Override
	public List<supliserpill> findByPrintfalse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserpill> findByPrintTrue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserpill> findBySupliserid(String Supliserid) {
		// TODO Auto-generated method stub
		return pillRepo.findBySupliserid(Supliserid);
	}

	@Override
	public List<supliserpill> findBySupliseridAndSuplisername(String Supliserid, String suplisername) {

		return pillRepo.findBySupliseridAndSuplisername(Supliserid, suplisername);

	}

	@Override
	public List<supliserpill> findBySuplisername(String suplisername) {
		// TODO Auto-generated method stub
		return pillRepo.findBySuplisername(suplisername);
	}

	@Override
	public List<supliserpill> findBySuplisernameAndEntrydateBetween(String suplisername, Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return pillRepo.findBySuplisernameAndEntrydateBetween(suplisername, fDate, sDate);
	}

	@Override
	public List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername, Date date) {
		// TODO Auto-generated method stub
		return pillRepo.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, date);
	}

	@Override
	public List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
			String suplisername, Date fDate, Date sDate) {
		// TODO Auto-generated method stub
		return pillRepo.findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(suplisername, fDate,
				sDate);
	}

	@Override
	public supliserpill findBySupliserpillid(String Supliserpillid) {
		// TODO Auto-generated method stub
		return pillRepo.findBySupliserpillid(Supliserpillid);
	}

	@Override
	public List<supliserpill> findForMonitor(String suplisername, String datefrom, String dateto)
			throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");
		List<supliserpill> pills = new ArrayList<supliserpill>();

		/*
		 * date from == null date to == null && datefrom !==null
		 *
		 *
		 *
		 */

		if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null") && datefrom == null
				|| datefrom.equals("") || datefrom.equals(" ") || datefrom.equals("null")) {
			pills = pillRepo.findBySuplisername(suplisername);

		} else {

			if (dateto == null || dateto.equals("") || dateto.equals(" ") || dateto.equals("null")) {
				datefrom = datefrom + " 00:00:00.0";

				pills = pillRepo.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, SDF.parse(datefrom));

			} else {
				datefrom = datefrom + " 00:00:00";
				dateto = dateto + " 23:59:59";

				pills = pillRepo.findBySuplisernameAndEntrydateBetween(suplisername, SDF.parse(datefrom),
						SDF.parse(dateto));
			}

		}

		return pills;
	}

	@Override
	public List<supliserpill> findSearchPill(String suplisername, String pillid, String from, String to)
			throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");
		List<supliserpill> finder = new ArrayList<supliserpill>();
		LOGGER.info("Sup name : " + suplisername + " Pill id : " + pillid + " fdate : " + from + " sDate : " + to);
		if (pillid == null || pillid.equals("") || pillid.equals("null") || pillid.equals("0")) {

			if (suplisername == null || suplisername.equals("") || suplisername.equals("null")) {
				if (from.equals(null) || from.equals("null") || from.equals("") || from.equals(" ")) {
					LOGGER.info("date from equals null repo getAll();");
					finder = pillRepo.findAll();
					return finder;
				} else {
					LOGGER.info("cheak date from and date to");
					from = from + START_TIME;

					if (to.equals(null) || to.equals("null") || to.equals("") || to.equals(" ")) {
						LOGGER.info("date to equals null method find By date from equals and greater than");
						finder = pillRepo.findByEntrydateGreaterThanEqual(SDF.parse(from));
						return finder;
					} else {

						LOGGER.info("2 dates not null find between date from & date to");
						to = to + END_TIME;

						finder = pillRepo.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(SDF.parse(from),
								SDF.parse(to));
						LOGGER.info(String.valueOf(finder.size()));
						return finder;
					}

				}

			} else {
				LOGGER.info("supliser name not null");

				if (from.equals(null) || from.equals("null") || from.equals("") || from.equals(" ")) {
					LOGGER.info("date from equals null repo find all by supliser name");
					finder = pillRepo.findBySuplisername(suplisername);
					return finder;
				} else {
					LOGGER.info("cheak date from and date to @ supliser name");
					from = from + START_TIME;

					if (to.equals(null) || to.equals("null") || to.equals("") || to.equals(" ")) {
						LOGGER.info(
								"date to equals null method find By date from equals and greater than by supliser name");
						finder = pillRepo.findBySuplisernameAndEntrydateGreaterThanEqual(suplisername, SDF.parse(from));
						return finder;
					} else {
						to = to + END_TIME;
						LOGGER.info("2 dates not null find between date from & date to by supliser name");

						finder = pillRepo.findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
								suplisername, SDF.parse(from), SDF.parse(to));
						return finder;
					}

				}

			}
		} else {

			LOGGER.info("Just find by pill id");

			finder.add(pillRepo.findById(Long.decode(pillid)).get());
			return finder;

		}

	}

	@Override
	public supliserpill minToPill(supliserpill supliserpill) {
		Optional<supliserpill> oldPill = findById(supliserpill.getId());

		supliserpill Row = new supliserpill();
		Row = oldPill.get();
		Row.setTotalafterdis(supliserpill.getTotalafterdis() - oldPill.get().getTotalafterdis());
		Row.setTotalbefordis(supliserpill.getTotalbefordis() - oldPill.get().getTotalbefordis());
		Row.setLastedit(new Date());

		return pillRepo.save(Row);
	}

	@Override
	public void open(Long id) {
		Optional<supliserpill> pill = pillRepo.findById(id);
		pill.get().setClosed(false);
		pillRepo.save(pill.get());

	}

	@Override
	public supliserpill plusToPill(supliserpill supliserpill) {
		Optional<supliserpill> oldPill = findById(supliserpill.getId());

		supliserpill Row = new supliserpill();
		Row = oldPill.get();
		Row.setTotalafterdis(supliserpill.getTotalafterdis() + oldPill.get().getTotalafterdis());
		Row.setTotalbefordis(supliserpill.getTotalbefordis() + oldPill.get().getTotalbefordis());
		Row.setLastedit(new Date());

		return pillRepo.save(Row);
	}

	@Override
	public supliserpill save(supliserpill supliserpill) {

		return pillRepo.save(supliserpill);
	}

	@Override
	public supliserpill update(supliserpill supliserpill) {

		return pillRepo.save(supliserpill);
	}

	@Override
	public supliserpill updateEdit(supliserpill supliserpill) {
		Optional<supliserpill> olddata = pillRepo.findById(supliserpill.getId());
		olddata.get().setLastedit(new Date());
		olddata.get().setEditemployeename(supliserpill.getEditemployeename());

		return olddata.get();
	}

	@Override
	public supliserpilldetails verifyLastDiscount(String productname) {
		List<supliserpilldetails> detailsList = pilldetailsRepo.findByProductname(productname);
		supliserpilldetails details = new supliserpilldetails();

		if (detailsList == null || detailsList.size() == 0) {

			return details;
		} else {

			details = detailsList.get(detailsList.size() - 1);
			System.out.println("Last Discount :" + details.getProductdiscount());
			return details;

		}

	}

}
