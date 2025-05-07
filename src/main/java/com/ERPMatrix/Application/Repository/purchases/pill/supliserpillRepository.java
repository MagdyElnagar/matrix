package com.ERPMatrix.Application.Repository.purchases.pill;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;

@Repository
public interface supliserpillRepository extends JpaRepository<supliserpill, Long> {

	List<supliserpill> findAllByEntrydate(Date date);

	List<supliserpill> findAllByEntrydateBetween(Date fDate, Date sDate);

	List<supliserpill> findByClosed(boolean colsed);

	List<supliserpill> findByEmployeename(String employee);

	List<supliserpill> findByEntrydateGreaterThanEqual(Date date);

	List<supliserpill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate);

	List<supliserpill> findByPrint(boolean print);

	List<supliserpill> findBySupliserid(String Supliserid);

	List<supliserpill> findBySupliseridAndSuplisername(String Supliserid, String suplisername);

	List<supliserpill> findBySuplisername(String suplisername);

	List<supliserpill> findBySuplisernameAndEntrydateBetween(String suplisername, Date fDate, Date sDate);

	List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername, Date date);

	List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String suplisername,
			Date fDate, Date sDate);

	supliserpill findBySupliserpillid(String Supliserpillid);

	supliserpill findOneById(Long id);

}
