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
@Table(name = "income_payment")

public class incomepayment {
	private String clientid;
	private String empname;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date payeddate;
	private double paymentamount;
	private double pillamount;
	private String pillid;

	public incomepayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public incomepayment(String empname, Long id, Date payeddate, double paymentamount, double pillamount,
			String pillid, String clientid) {
		super();
		this.empname = empname;
		this.id = id;
		this.payeddate = payeddate;
		this.paymentamount = paymentamount;
		this.pillamount = pillamount;
		this.pillid = pillid;
		this.clientid = clientid;
	}

	public String getClientid() {
		return clientid;
	}

	public String getEmpname() {
		return empname;
	}

	public Long getId() {
		return id;
	}

	public Date getPayeddate() {
		return payeddate;
	}

	public double getPaymentamount() {
		return paymentamount;
	}

	public double getPillamount() {
		return pillamount;
	}

	public String getPillid() {
		return pillid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPayeddate(Date payeddate) {
		this.payeddate = payeddate;
	}

	public void setPaymentamount(double paymentamount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.paymentamount = Double.parseDouble(df.format(paymentamount));
	}

	public void setPillamount(double pillamount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.pillamount = Double.parseDouble(df.format(pillamount));
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

}
