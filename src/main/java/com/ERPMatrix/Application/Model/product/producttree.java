package com.ERPMatrix.Application.Model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_tree")

public class producttree {
	private String benefit;
	private String company;
	private String composite;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prodcutid;
	private String section;
	private String type;

	public producttree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public producttree(Long id, String prodcutid, String company, String type, String composite, String benefit,
			String section) {
		super();
		this.id = id;
		this.prodcutid = prodcutid;
		this.company = company;
		this.type = type;
		this.composite = composite;
		this.benefit = benefit;
		this.section = section;

	}

	public String getBenefit() {
		return benefit;
	}

	public String getCompany() {
		return company;
	}

	public String getComposite() {
		return composite;
	}

	public Long getId() {
		return id;
	}

	public String getProdcutid() {
		return prodcutid;
	}

	public String getSection() {
		return section;
	}

	public String getType() {
		return type;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setComposite(String composite) {
		this.composite = composite;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProdcutid(String prodcutid) {
		this.prodcutid = prodcutid;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void setType(String type) {
		this.type = type;
	}

}
