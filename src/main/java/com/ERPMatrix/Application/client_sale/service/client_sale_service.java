package com.ERPMatrix.Application.client_sale.service;

import java.util.List;

import com.ERPMatrix.Application.client_sale.model.client_sale;

public interface client_sale_service {

	List<client_sale> findAll();

	List<client_sale> findByEmp(String name);

	List<client_sale> save(client_sale client_sale);

	List<client_sale> update(client_sale client_sale);

}
