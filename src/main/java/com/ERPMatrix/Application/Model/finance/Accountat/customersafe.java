package com.ERPMatrix.Application.Model.finance.Accountat;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Client_Safe")

public class customersafe {

	private String clientid;
	private String clientname;
	private String employeename;
	private Date entry;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double money;
	private String moneytaker;

	public customersafe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public customersafe(Long id, String employeename, String moneytaker, double money, Date entry, String clientname,
			String clientid) {
		super();
		this.id = id;
		this.employeename = employeename;
		this.moneytaker = moneytaker;
		this.money = money;
		this.entry = entry;
		this.clientname = clientname;
		this.clientid = clientid;
	}

	public String getClientid() {
		return clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public String getEmployeename() {
		return employeename;
	}

	public Date getEntry() {
		return entry;
	}

	public Long getId() {
		return id;
	}

	public double getMoney() {
		return money;
	}

	public String getMoneytaker() {
		return moneytaker;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public void setEntry(Date entry) {
		this.entry = entry;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMoney(double money) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.money = Double.parseDouble(df.format(money));
	}

	public void setMoneytaker(String moneytaker) {
		this.moneytaker = moneytaker;
	}

}
