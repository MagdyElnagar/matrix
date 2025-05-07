package com.ERPMatrix.Application.Model.purchases.Reback;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_reback_pill")

public class rebackPillModel {
	private String employeename;
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date pilldate;
	private String pillid;
	private double price;
	private String suplisername;

	public rebackPillModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public rebackPillModel(Long id, String suplisername, Date entrydate, String employeename, String pillid,
			Date pilldate, double price) {
		super();
		this.id = id;
		this.suplisername = suplisername;
		this.entrydate = entrydate;
		this.employeename = employeename;
		this.pillid = pillid;
		this.pilldate = pilldate;
		this.price = price;
	}

	public String getEmployeename() {
		return employeename;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Long getId() {
		return id;
	}

	public Date getPilldate() {
		return pilldate;
	}

	public String getPillid() {
		return pillid;
	}

	public double getPrice() {
		return price;
	}

	public String getSuplisername() {
		return suplisername;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPilldate(Date pilldate) {
		this.pilldate = pilldate;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSuplisername(String suplisername) {
		this.suplisername = suplisername;
	}

}
