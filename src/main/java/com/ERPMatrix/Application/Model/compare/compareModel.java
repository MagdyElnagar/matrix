package com.ERPMatrix.Application.Model.compare;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class compareModel {
	private long id;
	private String name;
	private double price;
	private double discount;
	private double companydiscount;

	public compareModel(long id, String name, double price, double discount, double companydiscount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.companydiscount = companydiscount;
	}

	public compareModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.price = Double.parseDouble(df.format(price));
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.discount = Double.parseDouble(df.format(discount));
	}

	public double getCompanydiscount() {
		return companydiscount;
	}

	public void setCompanydiscount(double companydiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.companydiscount = Double.parseDouble(df.format(companydiscount));
	}

}
