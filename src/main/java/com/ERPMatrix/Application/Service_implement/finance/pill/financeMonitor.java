package com.ERPMatrix.Application.Service_implement.finance.pill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Repository.finance.pill.pillRepository;
import com.ERPMatrix.Application.Service.finance.pill.financeMonitorServ;

@Service
public class financeMonitor implements financeMonitorServ {

	@Autowired
	private pillRepository pillRepo;

	@Override
	public List<pill> findAll() {
		// TODO Auto-generated method stub
		return pillRepo.findAll();
	}

}
