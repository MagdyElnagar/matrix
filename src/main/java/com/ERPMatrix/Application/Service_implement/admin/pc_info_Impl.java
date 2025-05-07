package com.ERPMatrix.Application.Service_implement.admin;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.User.UserPcInformation;
import com.ERPMatrix.Application.Repository.Admin.PC_INFO_Repo;
import com.ERPMatrix.Application.Service.admin.pc_info_Serv;

@Service
@Transactional
public class pc_info_Impl implements pc_info_Serv {

	@Autowired
	private PC_INFO_Repo pc_repo;

	@Override
	public List<UserPcInformation> findAll() {
		// TODO Auto-generated method stub
		return pc_repo.findAll();
	}

	@Override
	public UserPcInformation findByUsername(String username) {
		// TODO Auto-generated method stub
		return pc_repo.findByUsername(username);
	}

	@Override
	public UserPcInformation save(UserPcInformation UserPcInformation) {
		UserPcInformation pc = findByUsername(UserPcInformation.getUsername());

		if (pc == null) {

		} else {
			pc_repo.deleteById(pc.getId());

		}

		UserPcInformation.setDate(new Date());
		return pc_repo.save(UserPcInformation);
	}

}
