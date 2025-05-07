package com.ERPMatrix.Application.Service_implement.Client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.Accountat.customersafe;
import com.ERPMatrix.Application.Repository.finance.Accountat.customersafeRepo;
import com.ERPMatrix.Application.Service.Client.customrsafeServ;

@Service
public class customrsafeImpl implements customrsafeServ {

	@Autowired
	private customersafeRepo csRepo;

	@Override
	public List<customersafe> findByClientid(String cliid) {
		// TODO Auto-generated method stub
		return csRepo.findByClientid(cliid);
	}

	@Override
	public List<customersafe> findByClientname(String cliname) {
		// TODO Auto-generated method stub
		return csRepo.findByClientname(cliname);
	}

	@Override
	public List<customersafe> findByEntryGreatherThanEqual(Date date) {
		// TODO Auto-generated method stub
		return csRepo.findByEntryGreaterThanEqual(date);
	}

	@Override
	public customersafe save(customersafe customersafe) {
		// TODO Auto-generated method stub

		if (customersafe.getEntry() == null) {
			customersafe.setEntry(new Date());
		}
		return csRepo.save(customersafe);
	}

}
