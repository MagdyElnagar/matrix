package com.ERPMatrix.Application.Service.finance.Acountat;

import java.util.Date;
import java.util.List;

import com.ERPMatrix.Application.Model.finance.Accountat.incomepayment;

public interface incomepaymentServ {

	List<incomepayment> findAll();

	List<incomepayment> findByClientid(String clientid);

	List<incomepayment> findByPayeddateGreaterThanEqual(Date date);

	incomepayment findByPillid(String pillid);

	incomepayment save(incomepayment incomepayment);

}
