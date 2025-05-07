package com.ERPMatrix.Application.Model.product.view;

public class P_purchess_view {

	private double discount;
	private String patch;
	private String pilldate;
	private String pillid;
	private double price;
	private String productname;
	private int qty;
	private String supname;

	public P_purchess_view() {
		super();
		// TODO Auto-generated constructor stub
	}

	public P_purchess_view(double discount, String patch, String pilldate, String pillid, double price,
			String productname, int qty, String supname) {
		super();
		this.discount = discount;
		this.patch = patch;
		this.pilldate = pilldate;
		this.pillid = pillid;
		this.price = price;
		this.productname = productname;
		this.qty = qty;
		this.supname = supname;
	}

	public double getDiscount() {
		return discount;
	}

	public String getPatch() {
		return patch;
	}

	public String getPilldate() {
		return pilldate;
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

	public String getSupname() {
		return supname;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public void setPilldate(String pilldate) {
		this.pilldate = pilldate;
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

	public void setSupname(String supname) {
		this.supname = supname;
	}

}
