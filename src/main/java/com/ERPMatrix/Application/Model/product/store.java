package com.ERPMatrix.Application.Model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class store {
	private String address;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	public store() {
	}

	public store(String address, long id, String name) {
		super();
		this.address = address;
		this.id = id;
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
