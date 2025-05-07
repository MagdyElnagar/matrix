package com.ERPMatrix.Application.Service_implement.finance.Accountat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.Accountat.incomepayment;
import com.ERPMatrix.Application.Repository.finance.Accountat.incomepaymentRepo;
import com.ERPMatrix.Application.Service.finance.Acountat.incomepaymentServ;

@Service
public class incomepaymentImpl implements incomepaymentServ {

	@Autowired
	private incomepaymentRepo incomeRepo;

	@Override
	public List<incomepayment> findAll() {
		// TODO Auto-generated method stub
		return incomeRepo.findAll();
	}

	@Override
	public List<incomepayment> findByClientid(String clientid) {
		// TODO Auto-generated method stub
		return incomeRepo.findByClientid(clientid);
	}

	@Override
	public List<incomepayment> findByPayeddateGreaterThanEqual(Date date) {
		// TODO Auto-generated method stub
		return incomeRepo.findByPayeddateGreaterThanEqual(date);
	}

	@Override
	public incomepayment findByPillid(String pillid) {
		// TODO Auto-generated method stub
		return incomeRepo.findByPillid(pillid);
	}

	@Override
	public incomepayment save(incomepayment incomepayment) {
		// TODO Auto-generated method stub
		return incomeRepo.save(incomepayment);
	}

}
