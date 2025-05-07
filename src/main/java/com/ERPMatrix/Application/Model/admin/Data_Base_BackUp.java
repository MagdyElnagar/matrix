package com.ERPMatrix.Application.Model.admin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Data_base_back_Up")
public class Data_Base_BackUp {
	private String filename;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date messiondate;
	private boolean status;
	private String username;

	public Data_Base_BackUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data_Base_BackUp(Long id, String username, Date messiondate, boolean status, String filename) {
		super();
		this.id = id;
		this.username = username;
		this.messiondate = messiondate;
		this.status = status;
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public Long getId() {
		return id;
	}

	public Date getMessiondate() {
		return messiondate;
	}

	public String getUsername() {
		return username;
	}

	public boolean isStatus() {
		return status;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessiondate(Date messiondate) {
		this.messiondate = messiondate;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
