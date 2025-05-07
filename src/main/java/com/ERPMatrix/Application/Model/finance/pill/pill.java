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
@Table(name = "client_pill")

public class pill {

	private double amountafterdiscont;
	private double amountbefordiscount;
	private Date cashdate;
	private String clientname;
	private String clientrank;
	private boolean close;
	private String delegate;
	private String editemployee;
	private String employee;
	private Date entrydate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date lastedit;
	private boolean payed;
	private Date paymentdate;
	private boolean print;
	private int productnum;
	private String storename;
	private String traficline;

	public pill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pill(double amountafterdiscont, double amountbefordiscount, Date cashdate, String clientname,
			String clientrank, boolean close, String delegate, String editemployee, String employee, Date entrydate,
			Long id, Date lastedit, boolean payed, Date paymentdate, boolean print, int productnum, String traficline,
			String storename) {
		super();
		this.amountafterdiscont = amountafterdiscont;
		this.amountbefordiscount = amountbefordiscount;
		this.cashdate = cashdate;
		this.clientname = clientname;
		this.clientrank = clientrank;
		this.close = close;
		this.delegate = delegate;
		this.editemployee = editemployee;
		this.employee = employee;
		this.entrydate = entrydate;
		this.id = id;
		this.lastedit = lastedit;
		this.payed = payed;
		this.paymentdate = paymentdate;
		this.print = print;
		this.productnum = productnum;
		this.traficline = traficline;
		this.storename = storename;
	}

	public double getAmountafterdiscont() {
		return amountafterdiscont;
	}

	public double getAmountbefordiscount() {
		return amountbefordiscount;
	}

	public Date getCashdate() {
		return cashdate;
	}

	public String getClientname() {
		return clientname;
	}

	public String getClientrank() {
		return clientrank;
	}

	public String getDelegate() {
		return delegate;
	}

	public String getEditemployee() {
		return editemployee;
	}

	public String getEmployee() {
		return employee;
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

	public Date getPaymentdate() {
		return paymentdate;
	}

	public int getProductnum() {
		return productnum;
	}

	public String getStorename() {
		return storename;
	}

	public String getTraficline() {
		return traficline;
	}

	public boolean isClose() {
		return close;
	}

	public boolean isPayed() {
		return payed;
	}

	public boolean isPrint() {
		return print;
	}

	public void setAmountafterdiscont(double amountafterdiscont) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.amountafterdiscont = Double.parseDouble(df.format(amountafterdiscont));
	}

	public void setAmountbefordiscount(double amountbefordiscount) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.amountbefordiscount = Double.parseDouble(df.format(amountbefordiscount));
	}

	public void setCashdate(Date cashdate) {
		this.cashdate = cashdate;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public void setClientrank(String clientrank) {
		this.clientrank = clientrank;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public void setDelegate(String delegate) {
		this.delegate = delegate;
	}

	public void setEditemployee(String editemployee) {
		this.editemployee = editemployee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
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

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public void setTraficline(String traficline) {
		this.traficline = traficline;
	}

}
