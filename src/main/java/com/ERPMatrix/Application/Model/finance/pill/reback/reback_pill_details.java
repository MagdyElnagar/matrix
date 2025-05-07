package com.ERPMatrix.Application.Model.finance.pill.reback;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_reback_pill_details")
public class reback_pill_details {
	private double amount;
	private String cliname;
	private Date date;
	private String details;

	private double discount;
	private Date expire;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patch;
	private double price;
	private String product;
	private int qty;
	private String rebackid;

	public reback_pill_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public reback_pill_details(double amount, String cliname, Date date, String details, double discount, Date expire,
			Long id, String patch, double price, String product, int qty, String rebackid) {
		this.amount = amount;
		this.cliname = cliname;
		this.date = date;
		this.details = details;
		this.discount = discount;
		this.expire = expire;
		this.id = id;
		this.patch = patch;
		this.price = price;
		this.product = product;
		this.qty = qty;
		this.rebackid = rebackid;
	}

	public double getAmount() {
		return amount;
	}

	public String getCliname() {
		return cliname;
	}

	public Date getDate() {
		return date;
	}

	public String getDetails() {
		return details;
	}

	public double getDiscount() {
		return discount;
	}

	public Date getExpire() {
		return expire;
	}

	public Long getId() {
		return id;
	}

	public String getPatch() {
		return patch;
	}

	public double getPrice() {
		return price;
	}

	public String getProduct() {
		return product;
	}

	public int getQty() {
		return qty;
	}

	public String getRebackid() {
		return rebackid;
	}

	public void setAmount(double amount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.amount = Double.parseDouble(df.format(amount));
		this.amount = amount;
	}

	public void setCliname(String cliname) {
		this.cliname = cliname;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setDiscount(double discount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.discount = Double.parseDouble(df.format(discount));
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public void setPrice(double price) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.price = Double.parseDouble(df.format(price));
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setRebackid(String rebackid) {
		this.rebackid = rebackid;
	}

}
