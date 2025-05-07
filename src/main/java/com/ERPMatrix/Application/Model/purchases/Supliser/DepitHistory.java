package com.ERPMatrix.Application.Model.purchases.Supliser;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_depit_history")

public class DepitHistory {

	private String details;
	private String employee;
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String paymentyStatus;
	private String pillid;
	private double price;
	private String supliserid;
	private String suplisername;

	public DepitHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepitHistory(String details, String employee, Date entrydate, Long id, String paymentyStatus, String pillid,
			double price, String suplisername, String supliserid) {
		super();
		this.details = details;
		this.employee = employee;
		this.entrydate = entrydate;
		this.id = id;
		this.paymentyStatus = paymentyStatus;
		this.pillid = pillid;
		this.price = price;
		this.suplisername = suplisername;
		this.supliserid = supliserid;
	}

	public String getDetails() {
		return details;
	}

	public String getEmployee() {
		return employee;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Long getId() {
		return id;
	}

	public String getPaymentyStatus() {
		return paymentyStatus;
	}

	public String getPillid() {
		return pillid;
	}

	public double getPrice() {
		return price;
	}

	public String getSupliserid() {
		return supliserid;
	}

	public String getSuplisername() {
		return suplisername;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaymentyStatus(String paymentyStatus) {
		this.paymentyStatus = paymentyStatus;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setPrice(double price) {

		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.price = Double.parseDouble(df.format(price));

	}

	public void setSupliserid(String supliserid) {
		this.supliserid = supliserid;
	}

	public void setSuplisername(String suplisername) {
		this.suplisername = suplisername;
	}

}
