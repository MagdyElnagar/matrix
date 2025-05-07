package com.ERPMatrix.Application.Model.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_sort")
public class product_print_sort {

	private String empname;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sortby;

	public product_print_sort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public product_print_sort(Long id, String empname, String sortby) {
		super();
		this.id = id;
		this.empname = empname;
		this.sortby = sortby;
	}

	public String getEmpname() {
		return empname;
	}

	public Long getId() {
		return id;
	}

	public String getSortby() {
		return sortby;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

}
