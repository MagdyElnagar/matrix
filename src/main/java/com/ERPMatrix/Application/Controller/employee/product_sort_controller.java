package com.ERPMatrix.Application.Controller.employee;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.employee.product_print_sort;
import com.ERPMatrix.Application.Service.employee.product_sort_service;

@RestController
@RequestMapping(path = { "/", "/product_sort" })
@CrossOrigin(origins = "*")
public class product_sort_controller {

	@Autowired
	private product_sort_service product_sort;

	@GetMapping("/findByEmp/{user}")
	public ResponseEntity<product_print_sort> findByEmp(@PathVariable String user) {

		product_print_sort sorting = product_sort.findByEmployee(user);
		return new ResponseEntity<>(sorting, OK);
	}

	@PostMapping("/updateSorting/{sort}/{user}")
	public ResponseEntity<product_print_sort> updateSorting(@PathVariable String sort, @PathVariable String user) {

		product_print_sort sorting = product_sort.update(sort, user);
		return new ResponseEntity<>(sorting, OK);
	}

}
