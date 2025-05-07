package com.ERPMatrix.Application.client_sale.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Repository.Admin.cli_sale.client_sale_Repos;
import com.ERPMatrix.Application.client_sale.model.client_sale;
import com.ERPMatrix.Application.client_sale.service.client_sale_service;

@Service
@Transactional
public class client_sale_impl implements client_sale_service {

	@Autowired
	private client_sale_Repos client_sale_repo;

	@Override
	public List<client_sale> findAll() {
		// TODO Auto-generated method stub
		return client_sale_repo.findAll();
	}

	@Override
	public List<client_sale> findByEmp(String name) {
		// TODO Auto-generated method stub
		return client_sale_repo.findByTelesalseOrderByCalltime(name);
	}

	@Override
	public List<client_sale> save(client_sale client_sale) {
		client_sale_repo.save(client_sale);

		return findAll();
	}

	@Override
	public List<client_sale> update(client_sale client_sale) {
		client_sale cl = client_sale_repo.findByTelesalseAndClient(client_sale.getTelesalse(), client_sale.getClient());
		cl.setStatus(client_sale.isStatus());
		client_sale_repo.save(cl);
		System.out.println(client_sale.getTelesalse() + "  --  " + client_sale.getClient());
		System.out.println(cl.getClient());
		System.out.println(client_sale.isStatus());

		return findAll();
	}

}
