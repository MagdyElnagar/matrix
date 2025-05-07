package com.ERPMatrix.Application.client_sale.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.client_sale.model.client_sale;
import com.ERPMatrix.Application.client_sale.service.client_sale_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/client_sale/" })
@CrossOrigin(origins = "*")
public class client_sale_controller {

	@Autowired
	private client_sale_service client_sale_serv;

	@PostMapping("addEmp")
	public ResponseEntity<List<client_sale>> addEmp(@RequestBody client_sale client_sale) throws HandlerException {

		List<client_sale> product = client_sale_serv.save(client_sale);
		return new ResponseEntity<>(product, OK);
	}

	@GetMapping("findAll")
	public ResponseEntity<List<client_sale>> findAll() {

		List<client_sale> findAll = client_sale_serv.findAll();

		return new ResponseEntity<>(findAll, OK);
	}

	@PostMapping("findByEmp/{name}")
	public ResponseEntity<List<client_sale>> findByEmp(@PathVariable String name) {

		List<client_sale> findAll = client_sale_serv.findByEmp(name);

		return new ResponseEntity<>(findAll, OK);
	}

	@PostMapping("update")
	public ResponseEntity<List<client_sale>> update(@RequestBody client_sale client_sale) throws HandlerException {

		List<client_sale> product = client_sale_serv.update(client_sale);
		return new ResponseEntity<>(product, OK);
	}

}
