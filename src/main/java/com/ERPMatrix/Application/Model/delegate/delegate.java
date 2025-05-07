package com.ERPMatrix.Application.Model.delegate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delegate")
public class delegate {

	private String gov;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String name;
	private String phone;
	private String traficline;

	public delegate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public delegate(long id, String name, String traficline, String gov, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.traficline = traficline;
		this.gov = gov;
		this.phone = phone;
	}

	public String getGov() {
		return gov;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getTraficline() {
		return traficline;
	}

	public void setGov(String gov) {
		this.gov = gov;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTraficline(String traficline) {
		this.traficline = traficline;
	}

}
