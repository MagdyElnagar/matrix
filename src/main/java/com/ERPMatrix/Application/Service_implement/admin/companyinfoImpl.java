package com.ERPMatrix.Application.Service_implement.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.admin.companyinfo;
import com.ERPMatrix.Application.Repository.Admin.companyinfoRepo;
import com.ERPMatrix.Application.Service.admin.companyinfoServ;

@Service
public class companyinfoImpl implements companyinfoServ {

	
	
	@Autowired
	private companyinfoRepo companyinfoRepo;
	@Override
	public companyinfo findByConame(String name) {
		return companyinfoRepo.findByConame(name);
	}

	@Override
	public companyinfo update(companyinfo companyinfo) {
		
		return companyinfoRepo.save(companyinfo);
	}

	@Override
	public companyinfo findById(Long id) {
		Optional<companyinfo> companyinfo = companyinfoRepo.findById(id);
		return companyinfo.get();
	}

}
