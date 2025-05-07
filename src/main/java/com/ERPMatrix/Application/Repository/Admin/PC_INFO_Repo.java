package com.ERPMatrix.Application.Repository.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.User.UserPcInformation;

@Repository
public interface PC_INFO_Repo extends JpaRepository<UserPcInformation, Long> {

	UserPcInformation findByUsername(String username);
}
