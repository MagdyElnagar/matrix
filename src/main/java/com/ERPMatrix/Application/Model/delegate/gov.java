package com.ERPMatrix.Application.Model.delegate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delegate_gov")
public class gov {

	private String delegate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public gov() {
		super();
		// TODO Auto-generated constructor stub
	}

	public gov(String delegate, Long id, String name) {
		this.delegate = delegate;
		this.id = id;
		this.name = name;
	}

	public String getDelegate() {
		return delegate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
