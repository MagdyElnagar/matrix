package com.ERPMatrix.Application.Service.employee;

import com.ERPMatrix.Application.Model.employee.product_print_sort;

public interface product_sort_service {

	product_print_sort findByEmployee(String user);

	product_print_sort update(String soryBy, String user);

}
