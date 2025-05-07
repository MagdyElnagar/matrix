package com.ERPMatrix.Application.Model.purchases.Supliser;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_supliser")
public class supliser {

	private String adress;
	private String commercialregistrationno;
	private double cridet;
	private double customersafe;
	private double debit;
	private String details;
	private double formeraccount;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private boolean status;

	public supliser() {
	}

	public supliser(Long id, String name, String adress, String phone1, String phone2, String phone3, String phone4,
			double debit, double cridet, boolean status, String details, String commercialregistrationno,
			double formeraccount, double customersafe) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.debit = debit;
		this.cridet = cridet;
		this.status = status;
		this.details = details;
		this.commercialregistrationno = commercialregistrationno;
		this.formeraccount = formeraccount;
		this.customersafe = customersafe;
	}

	public String getAdress() {
		return adress;
	}

	public String getCommercialregistrationno() {
		return commercialregistrationno;
	}

	public double getCridet() {
		return cridet;
	}

	public double getCustomersafe() {
		return customersafe;
	}

	public double getDebit() {
		return debit;
	}

	public String getDetails() {
		return details;
	}

	public double getFormeraccount() {
		return formeraccount;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone1() {
		return phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public String getPhone4() {
		return phone4;
	}

	public boolean isStatus() {
		return status;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setCommercialregistrationno(String commercialregistrationno) {
		this.commercialregistrationno = commercialregistrationno;
	}

	public void setCridet(double cridet) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.cridet = Double.parseDouble(df.format(cridet));

	}

	public void setCustomersafe(double customersafe) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.customersafe = Double.parseDouble(df.format(customersafe));
	}

	public void setDebit(double debit) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.debit = Double.parseDouble(df.format(debit));

	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setFormeraccount(double formeraccount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.formeraccount = Double.parseDouble(df.format(formeraccount));
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	public void setStatus(boolean status) {

		this.status = status;
	}

}
