package com.ERPMatrix.Application.Model.finance.Accountat.staticResult;

public class AccountatSaleMonitoring {

	private double buyamount;
	private double buyrebackamount;

	private double creditor;
	private double debit;

	private Long id;
	private double payamount;

	private double saleamount;
	private double salerebackamount;

	private String suplisername;
	private double wepaythis;

	public AccountatSaleMonitoring() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountatSaleMonitoring(Long id, String suplisername, double saleamount, double salerebackamount,
			double buyamount, double buyrebackamount, double creditor, double debit, double payamount) {
		super();
		this.id = id;
		this.suplisername = suplisername;
		this.saleamount = saleamount;
		this.salerebackamount = salerebackamount;
		this.buyamount = buyamount;
		this.buyrebackamount = buyrebackamount;
		this.creditor = creditor;
		this.debit = debit;
		this.payamount = payamount;
	}

	public double getBuyamount() {
		return buyamount;
	}

	public double getBuyrebackamount() {
		return buyrebackamount;
	}

	public double getCreditor() {
		return creditor;
	}

	public double getDebit() {
		return debit;
	}

	public Long getId() {
		return id;
	}

	public double getPayamount() {
		return payamount;
	}

	public double getSaleamount() {
		return saleamount;
	}

	public double getSalerebackamount() {
		return salerebackamount;
	}

	public String getSuplisername() {
		return suplisername;
	}

	public void setBuyamount(double buyamount) {
		this.buyamount = buyamount;
	}

	public void setBuyrebackamount(double buyrebackamount) {
		this.buyrebackamount = buyrebackamount;
	}

	public void setCreditor(double creditor) {
		this.creditor = creditor;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}

	public void setSaleamount(double saleamount) {
		this.saleamount = saleamount;
	}

	public void setSalerebackamount(double salerebackamount) {
		this.salerebackamount = salerebackamount;
	}

	public void setSuplisername(String suplisername) {
		this.suplisername = suplisername;
	}

}
