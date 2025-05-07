package com.ERPMatrix.Application.Service.admin;

import com.ERPMatrix.Application.Model.admin.companyinfo;

public interface companyinfoServ {
	
	companyinfo findByConame(String name);
	companyinfo update(companyinfo companyinfo);
	companyinfo findById(Long id);

}
