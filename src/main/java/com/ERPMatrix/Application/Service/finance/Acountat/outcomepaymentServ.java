package com.ERPMatrix.Application.Service.finance.Acountat;

import java.util.Date;
import java.util.List;

import com.ERPMatrix.Application.Model.finance.Accountat.outcomepayment;

public interface outcomepaymentServ {
	List<outcomepayment> findAll();

	List<outcomepayment> findByClientid(String clientid);

	List<outcomepayment> findByPayeddateGreaterThanEqual(Date date);

	outcomepayment findByPillid(String pillid);

	outcomepayment save(outcomepayment outcomepayment);

}
