package com.ERPMatrix.Application.Service.admin.rank;

import java.util.List;

import com.ERPMatrix.Application.Model.admin.rank.rankmodel;

public interface rankServ {

	void delete(rankmodel rankmodel);

	List<rankmodel> findAll();

	rankmodel findByName(String name);

	rankmodel Save(rankmodel rankmodel);

}
