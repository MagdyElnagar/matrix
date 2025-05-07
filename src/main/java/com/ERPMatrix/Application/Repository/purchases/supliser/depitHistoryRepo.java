package com.ERPMatrix.Application.Repository.purchases.supliser;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;

@Repository
public interface depitHistoryRepo extends JpaRepository<DepitHistory, Long> {

	List<DepitHistory> findByEntrydate(Date entrydate);

	List<DepitHistory> findByEntrydateBetweenAndSuplisername(Date entrydate, Date date, String Suplisername);

	List<DepitHistory> findByEntrydateGreaterThanEqual(Date entrydate);

	List<DepitHistory> findByEntrydateGreaterThanEqualAndSuplisername(Date entrydate, String Suplisername);

	List<DepitHistory> findByPillid(String id);

	List<DepitHistory> findBySuplisername(String suplisername);

}
