package com.ERPMatrix.Application.Repository.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.admin.companyinfo;

@Repository
public interface companyinfoRepo extends JpaRepository<companyinfo, Long> {
	companyinfo findByConame(String name);
}
