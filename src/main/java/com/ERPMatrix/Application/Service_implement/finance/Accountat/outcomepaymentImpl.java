package com.ERPMatrix.Application.Service_implement.finance.Accountat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.Accountat.outcomepayment;
import com.ERPMatrix.Application.Repository.finance.Accountat.outcomepaymentRepo;
import com.ERPMatrix.Application.Service.finance.Acountat.outcomepaymentServ;

@Service
public class outcomepaymentImpl implements outcomepaymentServ {

	@Autowired
	private outcomepaymentRepo outcomeRepo;

	@Override
	public List<outcomepayment> findAll() {
		// TODO Auto-generated method stub
		return outcomeRepo.findAll();
	}

	@Override
	public List<outcomepayment> findByClientid(String clientid) {
		// TODO Auto-generated method stub
		return outcomeRepo.findByClientid(clientid);
	}

	@Override
	public List<outcomepayment> findByPayeddateGreaterThanEqual(Date date) {
		// TODO Auto-generated method stub
		return outcomeRepo.findByPayeddateGreaterThanEqual(date);
	}

	@Override
	public outcomepayment findByPillid(String pillid) {
		// TODO Auto-generated method stub
		return outcomeRepo.findByPillid(pillid);
	}

	@Override
	public outcomepayment save(outcomepayment outcomepayment) {
		// TODO Auto-generated method stub
		return outcomeRepo.save(outcomepayment);
	}

}
