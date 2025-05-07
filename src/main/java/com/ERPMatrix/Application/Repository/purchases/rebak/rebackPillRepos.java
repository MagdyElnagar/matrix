package com.ERPMatrix.Application.Repository.purchases.rebak;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;

@Repository
public interface rebackPillRepos extends JpaRepository  <rebackPillModel, Long> {
	
	rebackPillModel findByPillid(String pillid);
	List<rebackPillModel> findBySuplisername(String splisername);
	List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String suplisername ,Date fDate, Date sDate);
	List<rebackPillModel> findBySuplisernameAndEntrydateGreaterThanEqual(String suplisername , Date date);
	List<rebackPillModel> findBySuplisernameAndEntrydateBetween(String suplisername ,Date fDate, Date sDate);
	
}
