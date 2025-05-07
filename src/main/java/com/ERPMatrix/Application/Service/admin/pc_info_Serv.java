package com.ERPMatrix.Application.Service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.User.UserPcInformation;

@Service
public interface pc_info_Serv {

	List<UserPcInformation> findAll();

	UserPcInformation findByUsername(String username);

	UserPcInformation save(UserPcInformation UserPcInformation);

}
