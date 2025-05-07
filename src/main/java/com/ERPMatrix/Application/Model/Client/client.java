package com.ERPMatrix.Application.Model.Client;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Client")
public class client {

	private String adress;
	private String city;
	private String commercialregister;
	private double cridet;
	private String customrsafeid;
	private double debit;
	private String delegate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date joindate;
	private String name;
	private double payable;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private String rankname;
	private boolean status;
	private String trafic;

	public client() {
	}

	public client(String adress, String city, String commercialregister, double cridet, double debit, String delegate,
			Long id, Date joindate, String name, double payable, String phone1, String phone2, String phone3,
			String phone4, String rankname, boolean status, String trafic, String customrsafeid) {
		super();
		this.adress = adress;
		this.city = city;
		this.commercialregister = commercialregister;
		this.cridet = cridet;
		this.debit = debit;
		this.delegate = delegate;
		this.id = id;
		this.joindate = joindate;
		this.name = name;
		this.payable = payable;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.rankname = rankname;
		this.status = status;
		this.trafic = trafic;
		this.customrsafeid = customrsafeid;
	}

	public String getAdress() {
		return adress;
	}

	public String getCity() {
		return city;
	}

	public String getCommercialregister() {
		return commercialregister;
	}

	public double getCridet() {
		return cridet;
	}

	public String getCustomrsafeid() {
		return customrsafeid;
	}

	public double getDebit() {
		return debit;
	}

	public String getDelegate() {
		return delegate;
	}

	public Long getId() {
		return id;
	}

	public Date getJoindate() {
		return joindate;
	}

	public String getName() {
		return name;
	}

	public double getPayable() {
		return payable;
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

	public String getRankname() {
		return rankname;
	}

	public String getTrafic() {
		return trafic;
	}

	public boolean isStatus() {
		return status;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCommercialregister(String commercialregister) {
		this.commercialregister = commercialregister;
	}

	public void setCridet(double cridet) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.cridet = Double.parseDouble(df.format(cridet));

	}

	public void setCustomrsafeid(String customrsafeid) {
		this.customrsafeid = customrsafeid;
	}

	public void setDebit(double debit) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.debit = Double.parseDouble(df.format(debit));
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPayable(double payable) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.payable = Double.parseDouble(df.format(payable));

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

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setTrafic(String trafic) {
		this.trafic = trafic;
	}
}
