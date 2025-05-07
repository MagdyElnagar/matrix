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
@Table(name = "client_payed_pill")
public class paidpill {
	private String accountatemployee;
	private double clientavailableamount;
	private String clientname;
	private boolean closed;
	private Date date;
	private String employee;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double paymentamount;
	private Date paymentdate;
	private boolean paymentstatus;
	private double pillamount;
	private boolean print;
	private int productnumber;
	private String rankname;
	private double totalafterdiscount;
	private double totalbefordiscount;

	public paidpill() {
	}

	public paidpill(Long id, String clientname, Date date, double pillamount, int productnumber,
			double clientavailableamount, double totalafterdiscount, double totalbefordiscount, boolean print,
			boolean closed, boolean paymentstatus, double paymentamount, Date paymentdate, String rankname,
			String employee, String accountatemployee) {

		this.id = id;
		this.clientname = clientname;
		this.date = date;
		this.pillamount = pillamount;
		this.productnumber = productnumber;
		this.clientavailableamount = clientavailableamount;
		this.totalafterdiscount = totalafterdiscount;
		this.totalbefordiscount = totalbefordiscount;
		this.print = print;
		this.closed = closed;
		this.paymentstatus = paymentstatus;
		this.paymentamount = paymentamount;
		this.paymentdate = paymentdate;
		this.rankname = rankname;
		this.employee = employee;
		this.accountatemployee = accountatemployee;
	}

	public String getAccountatemployee() {
		return accountatemployee;
	}

	public double getClientavailableamount() {
		return clientavailableamount;
	}

	public String getClientname() {
		return clientname;
	}

	public Date getDate() {
		return date;
	}

	public String getEmployee() {
		return employee;
	}

	public Long getId() {
		return id;
	}

	public double getPaymentamount() {
		return paymentamount;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public double getPillamount() {
		return pillamount;
	}

	public int getProductnumber() {
		return productnumber;
	}

	public String getRankname() {
		return rankname;
	}

	public double getTotalafterdiscount() {
		return totalafterdiscount;
	}

	public double getTotalbefordiscount() {
		return totalbefordiscount;
	}

	public boolean isClosed() {
		return closed;
	}

	public boolean isPaymentstatus() {
		return paymentstatus;
	}

	public boolean isPrint() {
		return print;
	}

	public void setAccountatemployee(String accountatemployee) {
		this.accountatemployee = accountatemployee;
	}

	public void setClientavailableamount(double clientavailableamount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.clientavailableamount = Double.parseDouble(df.format(clientavailableamount));
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPaymentamount(double paymentamount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.paymentamount = Double.parseDouble(df.format(paymentamount));
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public void setPaymentstatus(boolean paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public void setPillamount(double pillamount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.pillamount = Double.parseDouble(df.format(pillamount));
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public void setProductnumber(int productnumber) {
		this.productnumber = productnumber;
	}

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	public void setTotalafterdiscount(double totalafterdiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.totalafterdiscount = Double.parseDouble(df.format(totalafterdiscount));
	}

	public void setTotalbefordiscount(double totalbefordiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.totalbefordiscount = Double.parseDouble(df.format(totalbefordiscount));
	}
}
