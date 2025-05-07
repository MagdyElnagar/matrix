package com.ERPMatrix.Application.Model.Phone;

public class call_Model {

	private String emp;
	private Long id;
	private String phone;

	public call_Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public call_Model(String emp, Long id, String phone) {
		this.emp = emp;
		this.id = id;
		this.phone = phone;
	}

	public String getEmp() {
		return emp;
	}

	public Long getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
