package com.ERPMatrix.Application.Model.purchases.Pill;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_pill_details")

public class supliserpilldetails {
	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pillid;
	private int productBounce;
	private double productdiscount;
	private String productname;
	private double productprice;
	private int productqouta;
	private String supname;
	private double totalafterdiscount;
	private double totalbeforiscount;

	public supliserpilldetails() {
	}

	public supliserpilldetails(Date date, Long id, String pillid, int productBounce, double productdiscount,
			String productname, double productprice, int productqouta, String supname, double totalafterdiscount,
			double totalbeforiscount) {
		this.date = date;
		this.id = id;
		this.pillid = pillid;
		this.productBounce = productBounce;
		this.productdiscount = productdiscount;
		this.productname = productname;
		this.productprice = productprice;
		this.productqouta = productqouta;
		this.supname = supname;
		this.totalafterdiscount = totalafterdiscount;
		this.totalbeforiscount = totalbeforiscount;
	}

	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public String getPillid() {
		return pillid;
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

	public String getSupname() {
		return supname;
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

	public void setPillid(String pillid) {
		this.pillid = pillid;
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

	public void setSupname(String supname) {
		this.supname = supname;
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
