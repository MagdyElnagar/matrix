package com.ERPMatrix.Application.Service.purchases.order;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface supliserorderServ {

	void convertopill(supliserorder supliserorder);

	List<supliserorder> findAll();

	List<supliserorder> findAllByEntrydate(Date date);

	List<supliserorder> findAllByEntrydateBetween(Date fDate, Date sDate);

	List<supliserorder> findByClosed(boolean colsed);

	List<supliserorder> findByEmployeename(String employee);

	List<supliserorder> findByEntrydateGreaterThanEqual(Date date);

	List<supliserorder> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate);

	supliserorder findById(Long id);

	List<supliserorder> findByPrint(boolean print);

	List<supliserorder> findByPrintfalse();

	List<supliserorder> findByPrintTrue();

	List<supliserorder> findBySupliserid(String Supliserid);

	List<supliserorder> findBySupliseridAndSuplisername(String Supliserid, String suplisername);

	List<supliserorder> findBySuplisername(String suplisername);

	supliserorder findBySupliserpillid(String Supliserpillid);

	List<supliserorder> findSearchOrder(String suplisername, String datefrom, String dateto) throws ParseException;

	supliserorder save(supliserorder supliserorder) throws HandlerException;

	supliserorder update(supliserorder supliserorder);

	supliserorder updateOrderAmount(supliserorder supliserorder);

}
