package com.ERPMatrix.Application.Model.purchases.Reback;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "purchases_reback_pill_details")

public class rebakDetailsModel {
	private int bounce;
	private Date date;
	private int discount;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productname;
	private double productprice;
	private int productqouta;
	private String rebackDetails;
	private String rebackid;
	private String supname;
	private double totalafterdiscount;

	public rebakDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public rebakDetailsModel(int bounce, int discount, Long id, String productname, double productprice,
			int productqouta, String rebackDetails, String rebackid, double totalafterdiscount, String supname,
			Date date) {
		this.bounce = bounce;
		this.discount = discount;
		this.id = id;
		this.productname = productname;
		this.productprice = productprice;
		this.productqouta = productqouta;
		this.rebackDetails = rebackDetails;
		this.rebackid = rebackid;
		this.totalafterdiscount = totalafterdiscount;
		this.supname = supname;
		this.date = date;
	}

	public int getBounce() {
		return bounce;
	}

	public Date getDate() {
		return date;
	}

	public int getDiscount() {
		return discount;
	}

	public Long getId() {
		return id;
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

	public String getRebackDetails() {
		return rebackDetails;
	}

	public String getRebackid() {
		return rebackid;
	}

	public String getSupname() {
		return supname;
	}

	public double getTotalafterdiscount() {
		return totalafterdiscount;
	}

	public void setBounce(int bounce) {
		this.bounce = bounce;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setRebackDetails(String rebackDetails) {
		this.rebackDetails = rebackDetails;
	}

	public void setRebackid(String rebackid) {
		this.rebackid = rebackid;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

	public void setTotalafterdiscount(double totalafterdiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.totalafterdiscount = Double.parseDouble(df.format(totalafterdiscount));
	}

}
