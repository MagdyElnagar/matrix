package com.ERPMatrix.Application.Service.finance.pill.reback;

import java.text.ParseException;
import java.util.List;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface reback_pill_service {

	void delete(reback_pill reback_pill);

	List<reback_pill> findAll();

	List<reback_pill> findByClientname(String clientname);

	List<reback_pill> findByClientnameAndEntrydateGreaterThanEqual(String clientname, String date)
			throws ParseException;

	List<reback_pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname,
			String fdate, String ldate) throws ParseException;

	List<reback_pill> findByEmployeename(String employeename);

	List<reback_pill> findByEntrydateGreaterThanEqual(String fdate) throws ParseException;

	List<reback_pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String fdate, String ldate)
			throws ParseException;

	reback_pill findById(Long id);

	reback_pill findByPillid(String pillid);

	reback_pill save(reback_pill reback_pill) throws NumberFormatException, HandlerException;

	reback_pill update(reback_pill reback_pill);
	

}
