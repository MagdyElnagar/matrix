package com.ERPMatrix.Application.Model.product.view;

public class P_purchess_reback_view {

	private String discount;
	private String patch;
	private String pillid;
	private double price;
	private String productname;
	private int qty;
	private String rebackdate;
	private String rebackid;
	private String supname;

	public P_purchess_reback_view() {
		super();
		// TODO Auto-generated constructor stub
	}

	public P_purchess_reback_view(String discount, String patch, String pillid, double price, String productname,
			int qty, String rebackdate, String rebackid, String supname) {
		super();
		this.discount = discount;
		this.patch = patch;
		this.pillid = pillid;
		this.price = price;
		this.productname = productname;
		this.qty = qty;
		this.rebackdate = rebackdate;
		this.rebackid = rebackid;
		this.supname = supname;
	}

	public String getDiscount() {
		return discount;
	}

	public String getPatch() {
		return patch;
	}

	public String getPillid() {
		return pillid;
	}

	public double getPrice() {
		return price;
	}

	public String getProductname() {
		return productname;
	}

	public int getQty() {
		return qty;
	}

	public String getRebackdate() {
		return rebackdate;
	}

	public String getRebackid() {
		return rebackid;
	}

	public String getSupname() {
		return supname;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setRebackdate(String rebackdate) {
		this.rebackdate = rebackdate;
	}

	public void setRebackid(String rebackid) {
		this.rebackid = rebackid;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

}
