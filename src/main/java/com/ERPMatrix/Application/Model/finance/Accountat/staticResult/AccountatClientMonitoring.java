package com.ERPMatrix.Application.Model.finance.Accountat.staticResult;

public class AccountatClientMonitoring {

	private double buyamount;
	private double buyrebackamount;

	private double creditor;
	private double debit;

	private Long id;
	private double payamount;

	private double saleamount;
	private double salerebackamount;

	private String clientname;
	private double wepaythis;
	
	
	
	
	
	
	
	
	
	
	
	
	public AccountatClientMonitoring(double buyamount, double buyrebackamount, double creditor, double debit, Long id,
			double payamount, double saleamount, double salerebackamount, String clientname, double wepaythis) {
		super();
		this.buyamount = buyamount;
		this.buyrebackamount = buyrebackamount;
		this.creditor = creditor;
		this.debit = debit;
		this.id = id;
		this.payamount = payamount;
		this.saleamount = saleamount;
		this.salerebackamount = salerebackamount;
		this.clientname = clientname;
		this.wepaythis = wepaythis;
	}
	public AccountatClientMonitoring() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getBuyamount() {
		return buyamount;
	}
	public void setBuyamount(double buyamount) {
		this.buyamount = buyamount;
	}
	public double getBuyrebackamount() {
		return buyrebackamount;
	}
	public void setBuyrebackamount(double buyrebackamount) {
		this.buyrebackamount = buyrebackamount;
	}
	public double getCreditor() {
		return creditor;
	}
	public void setCreditor(double creditor) {
		this.creditor = creditor;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPayamount() {
		return payamount;
	}
	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}
	public double getSaleamount() {
		return saleamount;
	}
	public void setSaleamount(double saleamount) {
		this.saleamount = saleamount;
	}
	public double getSalerebackamount() {
		return salerebackamount;
	}
	public void setSalerebackamount(double salerebackamount) {
		this.salerebackamount = salerebackamount;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public double getWepaythis() {
		return wepaythis;
	}
	public void setWepaythis(double wepaythis) {
		this.wepaythis = wepaythis;
	}
	
	
	
	
	
	
}
