package com.ERPMatrix.Application.Service.purchases.reback;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;

public interface rebackPillServ {
	
	List<rebackPillModel> findAll();
	rebackPillModel findById(int id);
	List<rebackPillModel> findBySuplisername(String splisername);
	List<rebackPillModel> findByentrydate(Date date);
	rebackPillModel save(rebackPillModel rebackPillModel);
	rebackPillModel findByPillid(String pillid);
	List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String suplisername ,Date fDate, Date sDate);
	List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername , Date date);
	List<rebackPillModel> findBySuplisernameAndEntrydateBetween(String suplisername ,Date fDate, Date sDate);
	List<rebackPillModel> findForMonitor(String suplisername ,String fDate, String sDate) throws ParseException;
	
	
	
	


}
