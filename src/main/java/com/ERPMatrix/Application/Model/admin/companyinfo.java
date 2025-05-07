package com.ERPMatrix.Application.Model.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Company_Information")

public class companyinfo {
	private String adress;
	private String commercialregister;
	private String coname;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logourl;
	private String manager;
	private String managerassist;
	private String managerassistphone;
	private String managerphone;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;

	public companyinfo() {
		super();
	}

	public companyinfo(Long id, String coname, String logourl, String adress, String phone1, String phone2,
			String phone3, String phone4, String manager, String managerphone, String managerassist,
			String managerassistphone, String commercialregister) {
		super();
		this.id = id;
		this.coname = coname;
		this.logourl = logourl;
		this.adress = adress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.manager = manager;
		this.managerphone = managerphone;
		this.managerassist = managerassist;
		this.managerassistphone = managerassistphone;
		this.commercialregister = commercialregister;
	}

	public String getAdress() {
		return adress;
	}

	public String getCommercialregister() {
		return commercialregister;
	}

	public String getConame() {
		return coname;
	}

	public Long getId() {
		return id;
	}

	public String getLogourl() {
		return logourl;
	}

	public String getManager() {
		return manager;
	}

	public String getManagerassist() {
		return managerassist;
	}

	public String getManagerassistphone() {
		return managerassistphone;
	}

	public String getManagerphone() {
		return managerphone;
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

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setCommercialregister(String commercialregister) {
		this.commercialregister = commercialregister;
	}

	public void setConame(String coname) {
		this.coname = coname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setManagerassist(String managerassist) {
		this.managerassist = managerassist;
	}

	public void setManagerassistphone(String managerassistphone) {
		this.managerassistphone = managerassistphone;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
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

}
