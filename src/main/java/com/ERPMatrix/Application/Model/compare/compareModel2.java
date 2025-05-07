package com.ERPMatrix.Application.Model.compare;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "comparesec")
public class compareModel2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private double price;
	private double discount;
	
	
	
	
	
	
	public compareModel2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public compareModel2(long id, String name, double price, double discount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
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
	
	
	
	

}
