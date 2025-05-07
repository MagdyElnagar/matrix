package com.ERPMatrix.Application.Repository.purchases.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;

@Repository
public interface supliserorderRepository extends JpaRepository<supliserorder, Long> {

	List<supliserorder> findAllByEntrydate(Date date);

	List<supliserorder> findAllByEntrydateBetween(Date fDate, Date sDate);

	List<supliserorder> findByClosed(boolean colsed);

	List<supliserorder> findByEmployeename(String employee);

	List<supliserorder> findByEntrydateGreaterThanEqual(Date date);

	List<supliserorder> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate);

	List<supliserorder> findByPrint(boolean print);

	List<supliserorder> findBySupliserid(String Supliserid);

	List<supliserorder> findBySupliseridAndSuplisername(String Supliserid, String suplisername);

	List<supliserorder> findBySuplisername(String suplisername);

	List<supliserorder> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername, Date Fdate);

	List<supliserorder> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String suplisername,
			Date fdate, Date Sdate);

	supliserorder findBySuplisernameAndId(String supliser, Long id);

	supliserorder findBySupliserpillid(String supliserorderid);

	supliserorder findOneById(Long id);
}
