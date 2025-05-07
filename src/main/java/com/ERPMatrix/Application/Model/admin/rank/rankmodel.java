package com.ERPMatrix.Application.Model.admin.rank;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Client_Rank")
public class rankmodel {
	private int crid;

	private int deserverecall;

	private double discount;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public rankmodel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public rankmodel(int crid, int deserverecall, double discount, Long id, String name) {
		super();
		this.crid = crid;
		this.deserverecall = deserverecall;
		this.discount = discount;
		this.id = id;
		this.name = name;
	}

	public int getCrid() {
		return crid;
	}

	public int getDeserverecall() {
		return deserverecall;
	}

	public double getDiscount() {
		return discount;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setCrid(int crid) {
		this.crid = crid;
	}

	public void setDeserverecall(int deserverecall) {
		this.deserverecall = deserverecall;
	}

	public void setDiscount(double discount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.discount = Double.parseDouble(df.format(discount));
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
