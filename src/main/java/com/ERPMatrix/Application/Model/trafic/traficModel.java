package com.ERPMatrix.Application.Model.trafic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Client_Trafic_Line")
public class traficModel {
	private String catid;
	private String city;
	private String delegate;
	private String distributor;
	private String gov;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public traficModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public traficModel(String delegate, Long id, String gov, String city, String distributor, String catid) {
		super();
		this.delegate = delegate;
		this.id = id;
		this.gov = gov;
		this.city = city;
		this.distributor = distributor;
		this.catid = catid;
	}

	public String getCatid() {
		return catid;
	}

	public String getCity() {
		return city;
	}

	public String getDelegate() {
		return delegate;
	}

	public String getDistributor() {
		return distributor;
	}

	public String getGov() {
		return gov;
	}

	public Long getId() {
		return id;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public void setGov(String gov) {
		this.gov = gov;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
