package com.ERPMatrix.Application.Model.product;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "product_patch")

public class productbatch {

	private String batch;
	private Date entrydate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expire;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String pillid;
	private double productdiscount;
	private String productid;
	private String productname;
	private double productprice;
	private int productqouta;
	private String store;
	private String supliser;

	public productbatch() {
	}

	public productbatch(String batch, Date entrydate, Date expire, Long id, String pillid, double productdiscount,
			String productid, String productname, double productprice, int productqouta, String store,
			String supliser) {
		super();
		this.batch = batch;
		this.entrydate = entrydate;
		this.expire = expire;
		this.id = id;
		this.pillid = pillid;
		this.productdiscount = productdiscount;
		this.productid = productid;
		this.productname = productname;
		this.productprice = productprice;
		this.productqouta = productqouta;
		this.store = store;
		this.supliser = supliser;
	}

	public String getBatch() {
		return batch;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Date getExpire() {
		return expire;
	}

	public Long getId() {
		return id;
	}

	public String getPillid() {
		return pillid;
	}

	public double getProductdiscount() {
		return productdiscount;
	}

	public String getProductid() {
		return productid;
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

	public String getStore() {
		return store;
	}

	public String getSupliser() {
		return supliser;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPillid(String pillid) {
		this.pillid = pillid;
	}

	public void setProductdiscount(double productdiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.productdiscount = Double.parseDouble(df.format(productdiscount));
	}

	public void setProductid(String productid) {
		this.productid = productid;
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

	public void setStore(String store) {
		this.store = store;
	}

	public void setSupliser(String supliser) {
		this.supliser = supliser;
	}

}
