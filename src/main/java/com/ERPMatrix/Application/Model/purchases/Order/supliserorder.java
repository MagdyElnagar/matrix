package com.ERPMatrix.Application.Model.purchases.Order;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchases_order")

public class supliserorder {
	private boolean closed;
	private Date dateofcoming;
	private String editemployeename;
	private String employeename;
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date lastedit;
	private int orderproductnum;
	private boolean print;
	private String store;
	private String supliserid;
	private String suplisername;
	private String supliserpillid;
	private double totalafterdis;
	private double totalbefordis;

	public supliserorder() {

	}

	public supliserorder(boolean closed, Date dateofcoming, String editemployeename, String employeename,
			Date entrydate, Long id, Date lastedit, int orderproductnum, boolean print, String store, String supliserid,
			String suplisername, String supliserpillid, double totalafterdis, double totalbefordis) {
		super();
		this.closed = closed;
		this.dateofcoming = dateofcoming;
		this.editemployeename = editemployeename;
		this.employeename = employeename;
		this.entrydate = entrydate;
		this.id = id;
		this.lastedit = lastedit;
		this.orderproductnum = orderproductnum;
		this.print = print;
		this.store = store;
		this.supliserid = supliserid;
		this.suplisername = suplisername;
		this.supliserpillid = supliserpillid;
		this.totalafterdis = totalafterdis;
		this.totalbefordis = totalbefordis;
	}

	public Date getDateofcoming() {
		return dateofcoming;
	}

	public String getEditemployeename() {
		return editemployeename;
	}

	public String getEmployeename() {
		return employeename;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Long getId() {
		return id;
	}

	public Date getLastedit() {
		return lastedit;
	}

	public int getOrderproductnum() {
		return orderproductnum;
	}

	public String getStore() {
		return store;
	}

	public String getSupliserid() {
		return supliserid;
	}

	public String getSuplisername() {
		return suplisername;
	}

	public String getSupliserpillid() {
		return supliserpillid;
	}

	public double getTotalafterdis() {
		return totalafterdis;
	}

	public double getTotalbefordis() {
		return totalbefordis;
	}

	public boolean isClosed() {
		return closed;
	}

	public boolean isPrint() {
		return print;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setDateofcoming(Date dateofcoming) {
		this.dateofcoming = dateofcoming;
	}

	public void setEditemployeename(String editemployeename) {
		this.editemployeename = editemployeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastedit(Date lastedit) {
		this.lastedit = lastedit;
	}

	public void setOrderproductnum(int orderproductnum) {
		this.orderproductnum = orderproductnum;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setSupliserid(String supliserid) {
		this.supliserid = supliserid;
	}

	public void setSuplisername(String suplisername) {
		this.suplisername = suplisername;
	}

	public void setSupliserpillid(String supliserpillid) {
		this.supliserpillid = supliserpillid;
	}

	public void setTotalafterdis(double totalafterdis) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.totalafterdis = Double.parseDouble(df.format(totalafterdis));

	}

	public void setTotalbefordis(double totalbefordis) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.totalbefordis = Double.parseDouble(df.format(totalbefordis));
	}

}
