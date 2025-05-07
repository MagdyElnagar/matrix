package com.ERPMatrix.Application.Model.purchases.Pill;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "purchases_pill")

public class supliserpill {
	private boolean closed;
	private double debittosupliser;
	private String details;
	private String editemployeename;
	private String employeename;
	@Temporal(TemporalType.TIMESTAMP)
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date lastedit;
	private int numberofprint;
	private boolean payed;
	private boolean print;
	private String store;
	private String supliserid;
	private String suplisername;
	private String supliserpillid;
	private double totalafterdis;
	private double totalbefordis;

	public supliserpill() {
		super();
	}

	public supliserpill(boolean closed, double debittosupliser, String editemployeename, String employeename,
			Date entrydate, long id, Date lastedit, int numberofprint, boolean print, String store, String supliserid,
			String suplisername, String supliserpillid, double totalafterdis, double totalbefordis, boolean payed,
			String details) {
		super();
		this.closed = closed;
		this.debittosupliser = debittosupliser;
		this.editemployeename = editemployeename;
		this.employeename = employeename;
		this.entrydate = entrydate;
		this.id = id;
		this.lastedit = lastedit;
		this.numberofprint = numberofprint;
		this.print = print;
		this.store = store;
		this.supliserid = supliserid;
		this.suplisername = suplisername;
		this.supliserpillid = supliserpillid;
		this.totalafterdis = totalafterdis;
		this.totalbefordis = totalbefordis;
		this.payed = payed;
		this.details = details;
	}

	public double getDebittosupliser() {
		return debittosupliser;
	}

	public String getDetails() {
		return details;
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

	public long getId() {
		return id;
	}

	public Date getLastedit() {
		return lastedit;
	}

	public int getNumberofprint() {
		return numberofprint;
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

	public boolean isPayed() {
		return payed;
	}

	public boolean isPrint() {
		return print;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setDebittosupliser(double debittosupliser) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.CEILING);
		this.debittosupliser = Double.parseDouble(df.format(debittosupliser));
	}

	public void setDetails(String details) {
		this.details = details;
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

	public void setId(long id) {
		this.id = id;
	}

	public void setLastedit(Date lastedit) {
		this.lastedit = lastedit;
	}

	public void setNumberofprint(int numberofprint) {
		this.numberofprint = numberofprint;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
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
