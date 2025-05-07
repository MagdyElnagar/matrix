package com.ERPMatrix.Application.Repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.employee.product_print_sort;

@Repository
public interface product_sort_repo extends JpaRepository<product_print_sort, Long> {

	product_print_sort findByEmpname(String empname);

}
