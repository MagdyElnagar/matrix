package com.ERPMatrix.Application.Model.finance.pill;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_pill_details")

public class pilldetails {

	private double amount;
	private double amountafterdicount;

	private String cliname;
	private String company;
	private Date date;
	private double discount;
	private String employee;
	private Date expire;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patch;
	private String patchpillid;
	private String pillid;
	private double price;
	private String productname;
	private int qouta;

	public pilldetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pilldetails(double amount, double amountafterdicount, String cliname, String company, double discount,
			String employee, Date expire, Long id, String patch, String patchpillid, String pillid, double price,
			Date date, String productname, int qouta) {
		this.amount = amount;
		this.amountafterdicount = amountafterdicount;
		this.cliname = cliname;
		this.company = company;
		this.discount = discount;
		this.employee = employee;
		this.expire = expire;
		this.id = id;
		this.patch = patch;
		this.patchpillid = patchpillid;
		this.pillid = pillid;
		this.price = price;
		this.date = date;
		this.productname = productname;
		this.qouta = qouta;
	}

	public double getAmount() {
		return amount;
	}

	public double getAmountafterdicount() {
		return amountafterdicount;
	}

	public String getCliname() {
		return cliname;
	}

	public String getCompany() {
		return company;
	}

	public Date getDate() {
		return date;
	}

	public double getDiscount() {
		return discount;
	}

	public String getEmployee() {
		return employee;
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

	public String getPatchpillid() {
		return patchpillid;
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

	public int getQouta() {
		return qouta;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAmountafterdicount(double amountafterdicount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.amountafterdicount = Double.parseDouble(df.format(amountafterdicount));
	}

	public void setCliname(String cliname) {
		this.cliname = cliname;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDiscount(double discount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.discount = Double.parseDouble(df.format(discount));
	}

	public void setEmployee(String employee) {
		this.employee = employee;
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

	public void setPatchpillid(String patchpillid) {
		this.patchpillid = patchpillid;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setPrice(double price) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.price = Double.parseDouble(df.format(price));
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setQouta(int qouta) {
		this.qouta = qouta;
	}

}
