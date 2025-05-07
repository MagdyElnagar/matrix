package com.ERPMatrix.Application.Model.Jasper_Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

public class finance_Details {

	private Long id;
	private Date entrydate;
	private Double sales;
	private Double reback;
	private Double paid;
	private Double total;

	public finance_Details(Long id, Date entrydate, Double sales, Double reback, Double paid, Double total) {
		super();
		this.id = id;
		this.entrydate = entrydate;
		this.sales = sales;
		this.reback = reback;
		this.paid = paid;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.sales = Double.parseDouble(df.format(sales));
	}

	public Double getReback() {
		return reback;
	}

	public void setReback(Double reback) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.reback = Double.parseDouble(df.format(reback));
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.paid = Double.parseDouble(df.format(paid));
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		this.total = Double.parseDouble(df.format(total));
	}

}
