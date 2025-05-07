package com.ERPMatrix.Application.Model.product;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class product {

	private int carton;
	private String company;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private double limitprice;
	private int limitqouta;
	private String name;
	private int packet;
	private double price;
	private int qouta;
	private boolean status;
	private double weigheddiscount;

	public product() {
	}

	public product(Long id, String name, double price, double limitprice, String company, int packet, int carton,
			double weigheddiscount, int qouta, int limitqouta, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.limitprice = limitprice;
		this.company = company;
		this.packet = packet;
		this.carton = carton;
		this.weigheddiscount = weigheddiscount;
		this.qouta = qouta;
		this.limitqouta = limitqouta;
		this.status = status;
	}

	public int getCarton() {
		return carton;
	}

	public String getCompany() {
		return company;
	}

	public Long getId() {
		return id;
	}

	public double getLimitprice() {

		return limitprice;
	}

	public int getLimitqouta() {
		return limitqouta;
	}

	public String getName() {
		return name;
	}

	public int getPacket() {
		return packet;
	}

	public double getPrice() {
		return price;
	}

	public int getQouta() {
		return qouta;
	}

	public double getWeigheddiscount() {
		return weigheddiscount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setCarton(int carton) {
		this.carton = carton;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLimitprice(double limitprice) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.limitprice = Double.parseDouble(df.format(limitprice));
	}

	public void setLimitqouta(int limitqouta) {
		this.limitqouta = limitqouta;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPacket(int packet) {
		this.packet = packet;
	}

	public void setPrice(double price) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.price = Double.parseDouble(df.format(price));

	}

	public void setQouta(int qouta) {
		this.qouta = qouta;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setWeigheddiscount(double weigheddiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.weigheddiscount = Double.parseDouble(df.format(weigheddiscount));
	}

}
