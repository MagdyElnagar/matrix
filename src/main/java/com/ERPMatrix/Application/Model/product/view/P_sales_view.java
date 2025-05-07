package com.ERPMatrix.Application.Model.product.view;

public class P_sales_view {

	private String cliname;
	private String discount;
	private String patch;
	private String pilldate;
	private String pillid;
	private double price;
	private String productname;
	private int qty;

	public P_sales_view() {
		super();
		// TODO Auto-generated constructor stub
	}

	public P_sales_view(String cliname, String discount, String patch, String pilldate, String pillid, double price,
			String productname, int qty) {
		super();
		this.cliname = cliname;
		this.discount = discount;
		this.patch = patch;
		this.pilldate = pilldate;
		this.pillid = pillid;
		this.price = price;
		this.productname = productname;
		this.qty = qty;
	}

	public String getCliname() {
		return cliname;
	}

	public String getDiscount() {
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

	public void setCliname(String cliname) {
		this.cliname = cliname;
	}

	public void setDiscount(String discount) {
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

}
