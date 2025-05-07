package com.ERPMatrix.Application.Service.finance.pill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface pillServ {

	void delete(pill pill);

	pill edit(pill pill, double amount, double amountAfterDis, char oper) throws HandlerException;

	List<pill> findAll();

	List<pill> findAllByEntrydateBetween(String fDate, String sDate) throws ParseException;

	List<pill> findByClientname(String clientname);

	List<pill> findByClosed(boolean colsed, String date) throws ParseException;

	List<pill> findByDelegate(String delegate);

	List<pill> findByEmployee(String employee);

	List<pill> findByEntrydateGreaterThanEqual(Date date);

	List<pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname, String fDate, String sDate)
			throws ParseException;

	pill findById(Long id) throws HandlerException;

	List<pill> findByPrint(boolean print, String date) throws ParseException;

	List<pill> findByTraficline(String traficline);

	List<pill> findByTraficlineAndDate(String traficline, String date) throws ParseException;

	pill save(pill pill) throws ParseException;

	pill update(pill pill);

	List<pill> findByClientnameAndEntrydateGreaterThanEqual(String clientname,Date date);

	List<pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname, String fDate,
			String sDate) throws ParseException;

	List<pill> findByClientnameAndEntrydateBetween(String clientname, String fDate, String sDate) throws ParseException;
}
