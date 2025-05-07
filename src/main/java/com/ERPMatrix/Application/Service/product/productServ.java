package com.ERPMatrix.Application.Service.product;

import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface productServ {

	void deleteById(Long id);

	List<product> findAll();

	List<product> findByGreatherthan(int qouta);

	Optional<product> findById(Long id);

	product findByName(String name) throws HandlerException;

	List<product> findByNameIsContaining(String product);

	List<product> findByStatus(boolean status);

	List<product> findByZeroQouta(int qouta);

	product minFromProduct(String name, int qty) throws HandlerException;

	product plusToProduct(String name, int qty) throws HandlerException;

	product Save(product product) throws HandlerException;

	product update(product product) throws HandlerException;

}
