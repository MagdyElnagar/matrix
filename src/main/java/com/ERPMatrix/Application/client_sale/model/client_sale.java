package com.ERPMatrix.Application.client_sale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_sale")
public class client_sale {

	private String calltime;
	private String client;
	private String comment;
	private String delegate;
	private String details;
	private String editemp;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String phone;
	private String phone2;
	private String phone3;
	private boolean status;
	private String telesalse;

	public client_sale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public client_sale(String calltime, String client, String comment, String delegate, String details, String editemp,
			Long id, String phone, String phone2, String phone3, boolean status, String telesalse) {
		super();
		this.calltime = calltime;
		this.client = client;
		this.comment = comment;
		this.delegate = delegate;
		this.details = details;
		this.editemp = editemp;
		this.id = id;
		this.phone = phone;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.status = status;
		this.telesalse = telesalse;
	}

	public String getCalltime() {
		return calltime;
	}

	public String getClient() {
		return client;
	}

	public String getComment() {
		return comment;
	}

	public String getDelegate() {
		return delegate;
	}

	public String getDetails() {
		return details;
	}

	public String getEditemp() {
		return editemp;
	}

	public Long getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public String getTelesalse() {
		return telesalse;
	}

	public boolean isStatus() {
		return status;
	}

	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setEditemp(String editemp) {
		this.editemp = editemp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setTelesalse(String telesalse) {
		this.telesalse = telesalse;
	}

}
