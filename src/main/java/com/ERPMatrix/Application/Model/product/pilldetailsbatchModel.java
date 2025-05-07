package com.ERPMatrix.Application.Model.product;

import java.util.Date;

public class pilldetailsbatchModel {

	private String batch;
	private Date expire;
	private Long id;
	private String name;
	private String pillid;
	private int qouta;

	public pilldetailsbatchModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pilldetailsbatchModel(Long id, String name, int qouta, Date expire, String batch, String pillid) {
		super();
		this.id = id;
		this.name = name;
		this.qouta = qouta;
		this.expire = expire;
		this.batch = batch;
		this.pillid = pillid;
	}

	public String getBatch() {
		return batch;
	}

	public Date getExpire() {
		return expire;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPillid() {
		return pillid;
	}

	public int getQouta() {
		return qouta;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setQouta(int qouta) {
		this.qouta = qouta;
	}

}
