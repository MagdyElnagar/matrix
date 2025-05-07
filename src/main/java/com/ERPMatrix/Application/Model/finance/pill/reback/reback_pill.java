package com.ERPMatrix.Application.Model.finance.pill.reback;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_reback_pill")

public class reback_pill {
	private double amount;
	private String clientname;
	private String employeename;
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pillid;

	public reback_pill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public reback_pill(double amount, String clientname, String employeename, Date entrydate, Long id, String pillid) {
		super();
		this.amount = amount;
		this.clientname = clientname;
		this.employeename = employeename;
		this.entrydate = entrydate;
		this.id = id;
		this.pillid = pillid;
	}

	public double getAmount() {
		return amount;
	}

	public String getClientname() {
		return clientname;
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

	public String getPillid() {
		return pillid;
	}

	public void setAmount(double amount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.amount = Double.parseDouble(df.format(amount));

	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
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

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

}
