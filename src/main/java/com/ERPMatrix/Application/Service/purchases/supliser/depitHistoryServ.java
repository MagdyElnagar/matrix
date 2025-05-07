package com.ERPMatrix.Application.Service.purchases.supliser;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;

@Service
public interface depitHistoryServ {

	void delete(DepitHistory DepitHistory);

	List<DepitHistory> findByEntrydateGreaterThanEqualAndSuplisername(Date entrydate, String Suplisername);

	List<DepitHistory> findByEntrydateGreatherThanEqual(Date date);

	DepitHistory findById(Long id);

	List<DepitHistory> findByPillid(String id);

	double findByPillIdReturnOneValue(String pillid);

	List<DepitHistory> findBySuplisername(String supliser);

	DepitHistory save(DepitHistory DepitHistory);

	DepitHistory saveDepitHistory(DepitHistory DepitHistory);

}
