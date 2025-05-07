package com.ERPMatrix.Application.Model.purchases.Order;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_order_details")

public class orderdetails {
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderid;
	private int productBounce;
	private double productdiscount;
	private String productname;
	private double productprice;
	private int productqouta;
	private double totalafterdiscount;
	private double totalbeforiscount;

	public orderdetails() {
	}

	public orderdetails(Long id, String productname, String orderid, double productprice, double productdiscount,
			double totalafterdiscount, double totalbeforiscount, int productqouta, int productBounce, Date date) {
		super();
		this.id = id;
		this.productname = productname;
		this.orderid = orderid;
		this.productprice = productprice;
		this.productdiscount = productdiscount;
		this.totalafterdiscount = totalafterdiscount;
		this.totalbeforiscount = totalbeforiscount;
		this.productqouta = productqouta;
		this.productBounce = productBounce;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public String getOrderid() {
		return orderid;
	}

	public int getProductBounce() {
		return productBounce;
	}

	public double getProductdiscount() {
		return productdiscount;
	}

	public String getProductname() {
		return productname;
	}

	public double getProductprice() {

		return productprice;
	}

	public int getProductqouta() {
		return productqouta;
	}

	public double getTotalafterdiscount() {
		return totalafterdiscount;
	}

	public double getTotalbeforiscount() {

		return totalbeforiscount;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setProductBounce(int productBounce) {
		this.productBounce = productBounce;
	}

	public void setProductdiscount(double productdiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.productdiscount = Double.parseDouble(df.format(productdiscount));
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setProductprice(double productprice) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.productprice = Double.parseDouble(df.format(productprice));

	}

	public void setProductqouta(int productqouta) {
		this.productqouta = productqouta;
	}

	public void setTotalafterdiscount(double totalafterdiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.totalafterdiscount = Double.parseDouble(df.format(totalafterdiscount));
	}

	public void setTotalbeforiscount(double totalbeforiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.totalbeforiscount = Double.parseDouble(df.format(totalbeforiscount));
	}

}
