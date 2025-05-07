package com.ERPMatrix.Application.Service_implement.purchases.supliser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;
import com.ERPMatrix.Application.Repository.purchases.supliser.depitHistoryRepo;
import com.ERPMatrix.Application.Service.purchases.pill.salespillServ;
import com.ERPMatrix.Application.Service.purchases.supliser.depitHistoryServ;

@Service
@Transactional
public class depitHistoryImpl implements depitHistoryServ {

	@Autowired
	private depitHistoryRepo depitHistoryRepo;
	@Autowired

	private salespillServ salespillServ;

	@Override
	public void delete(DepitHistory DepitHistory) {
		// TODO Auto-generated method stub
		depitHistoryRepo.delete(DepitHistory);

	}

	@Override
	public List<DepitHistory> findByEntrydateGreaterThanEqualAndSuplisername(Date entrydate, String Suplisername) {
		// TODO Auto-generated method stub
		return depitHistoryRepo.findByEntrydateGreaterThanEqualAndSuplisername(entrydate, Suplisername);
	}

	@Override
	public List<DepitHistory> findByEntrydateGreatherThanEqual(Date date) {
		// TODO Auto-generated method stub
		return depitHistoryRepo.findByEntrydateGreaterThanEqual(date);
	}

	@Override
	public DepitHistory findById(Long id) {
		Optional<DepitHistory> dep = depitHistoryRepo.findById(id);

		return dep.get();
	}

	@Override
	public List<DepitHistory> findByPillid(String id) {
		// TODO Auto-generated method stub
		return depitHistoryRepo.findByPillid(id);
	}

	@Override
	public double findByPillIdReturnOneValue(String pillid) {
		// TODO Auto-generated method stub
		List<DepitHistory> depit = findByPillid(pillid);
		DepitHistory dp = new DepitHistory();
		System.out.println("Row pill id  : " + pillid);

		double z = 0;
		for (int x = 0; x < depit.size(); x++) {

			z = z + depit.get(x).getPrice();

		}
		System.out.println("Row pill z  : " + z);

		return z;
	}

	@Override
	public List<DepitHistory> findBySuplisername(String supliser) {
		// TODO Auto-generated method stub
		return depitHistoryRepo.findBySuplisername(supliser);
	}

	@Override
	public DepitHistory save(DepitHistory DepitHistory) {

		if (DepitHistory.getEntrydate() == null || DepitHistory.getEntrydate().equals("")) {
			DepitHistory.setEntrydate(new Date());
		}
		return depitHistoryRepo.save(DepitHistory);

	}

	@Override
	public DepitHistory saveDepitHistory(DepitHistory DepitHistory) {
		Optional<supliserpill> pill = salespillServ.findById(Long.decode(DepitHistory.getPillid()));
		List<DepitHistory> depitList = findByPillid(DepitHistory.getPillid());
		double Payed = 0;
		if (depitList == null) {

		} else {

			for (int x = 0; x < depitList.size(); x++) {
				Payed = Payed + depitList.get(x).getPrice();

			}
			Payed = Payed + DepitHistory.getPrice();

		}

		if (pill.get().getTotalafterdis() == DepitHistory.getPrice()) {

			pill.get().setPayed(true);
			salespillServ.save(pill.get());
		} else if (Payed >= pill.get().getTotalafterdis()) {

			pill.get().setPayed(true);
			pill.get().setDetails("تم دفع الفاتورة بالكامل");

			salespillServ.save(pill.get());
		} else {

			pill.get().setDetails("تم دفع جزء من الفاتورة");

		}

		if (DepitHistory.getEntrydate() == null || DepitHistory.getEntrydate().equals("")) {
			DepitHistory.setEntrydate(new Date());
		}
		return depitHistoryRepo.save(DepitHistory);
	}

}
