package com.ERPMatrix.Application.Service.purchases.pill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;

public interface salespillServ {

	supliserpill changeName(Long pillid, String oldName, String newName);

	supliserpill changePillSupliser(supliserpill supliserpill);

	void close(Long id);

	List<supliserpill> findAll();

	List<supliserpill> findAllByEntrydate(Date date);

	List<supliserpill> findAllByEntrydateBetween(Date fDate, Date sDate);

	List<supliserpill> findByClosed(boolean colsed);

	List<supliserpill> findByEmployeename(String employee);

	List<supliserpill> findByEntrydateGreaterThanEqual(Date date);

	List<supliserpill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate);

	Optional<supliserpill> findById(Long id);

	List<supliserpill> findByPrint(boolean print);

	List<supliserpill> findByPrintfalse();

	List<supliserpill> findByPrintTrue();

	List<supliserpill> findBySupliserid(String Supliserid);

	List<supliserpill> findBySupliseridAndSuplisername(String Supliserid, String suplisername);

	List<supliserpill> findBySuplisername(String suplisername);

	List<supliserpill> findBySuplisernameAndEntrydateBetween(String suplisername, Date fDate, Date sDate);

	List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername, Date date);

	List<supliserpill> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String suplisername,
			Date fDate, Date sDate);

	supliserpill findBySupliserpillid(String Supliserpillid);

	List<supliserpill> findForMonitor(String suplisername, String fDate, String sDate) throws ParseException;

	List<supliserpill> findSearchPill(String suplisername, String pillid, String from, String to) throws ParseException;

	supliserpill minToPill(supliserpill supliserpill);

	void open(Long id);

	supliserpill plusToPill(supliserpill supliserpill);

	supliserpill save(supliserpill supliserpill);

	supliserpill update(supliserpill supliserpill);

	supliserpill updateEdit(supliserpill supliserpill);

	supliserpilldetails verifyLastDiscount(String productname);

}
