package com.ERPMatrix.Application.Service_implement.employee;

import static com.ERPMatrix.Application.Constant.emplyee.EMPLOYEE_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.employee.product_print_sort;
import com.ERPMatrix.Application.Repository.employee.product_sort_repo;
import com.ERPMatrix.Application.Service.employee.product_sort_service;

@Service
public class product_sort_impl implements product_sort_service {

	@Autowired
	private product_sort_repo product_sort_repo;

	@Override
	public product_print_sort findByEmployee(String user) {
		// TODO Auto-generated method stub
		return product_sort_repo.findByEmpname(EMPLOYEE_NAME);
	}

	@Override
	public product_print_sort update(String soryBy, String user) {
		product_print_sort sort = findByEmployee(user);
		product_print_sort newSort = new product_print_sort();

		if (sort == null) {
			newSort.setEmpname(user);
			newSort.setSortby(soryBy);
			product_sort_repo.save(newSort);
		} else {
			sort.setSortby(soryBy);
			product_sort_repo.save(sort);
		}

		return findByEmployee(user);
	}

}
