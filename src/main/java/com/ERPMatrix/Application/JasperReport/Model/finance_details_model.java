package com.ERPMatrix.Application.JasperReport.Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

public class finance_details_model {

	private double amountafterdiscont;
	private double amountbefordiscount;
	private double cli_Cridet;
	private double cli_debit;
	private String client_name;
	private Date entrydate;
	private Long id;
	private double payed;
	private String pill_id;
	private double reback_amount;
	private double total_pill;
	private double unpayed;

	public finance_details_model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public finance_details_model(double amountafterdiscont, double amountbefordiscount, double cli_Cridet,
			double cli_debit, String client_name, Date entrydate, Long id, String pill_id, double reback_amount,
			double total_pill, double payed, double unpayed) {
		this.amountafterdiscont = amountafterdiscont;
		this.amountbefordiscount = amountbefordiscount;
		this.cli_Cridet = cli_Cridet;
		this.cli_debit = cli_debit;
		this.client_name = client_name;
		this.entrydate = entrydate;
		this.id = id;
		this.pill_id = pill_id;
		this.reback_amount = reback_amount;
		this.total_pill = total_pill;
		this.payed = payed;
		this.unpayed = unpayed;
	}

	public double getAmountafterdiscont() {
		return amountafterdiscont;
	}

	public double getAmountbefordiscount() {
		return amountbefordiscount;
	}

	public double getCli_Cridet() {
		return cli_Cridet;
	}

	public double getCli_debit() {
		return cli_debit;
	}

	public String getClient_name() {
		return client_name;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Long getId() {
		return id;
	}

	public double getPayed() {
		return payed;
	}

	public String getPill_id() {
		return pill_id;
	}

	public double getReback_amount() {
		return reback_amount;
	}

	public double getTotal_pill() {
		return total_pill;
	}

	public double getUnpayed() {
		return unpayed;
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

	public void setCli_Cridet(double cli_Cridet) {
		this.cli_Cridet = cli_Cridet;
	}

	public void setCli_debit(double cli_debit) {
		this.cli_debit = cli_debit;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPayed(double payed) {
		this.payed = payed;
	}

	public void setPill_id(String pill_id) {
		this.pill_id = pill_id;
	}

	public void setReback_amount(double reback_amount) {
		this.reback_amount = reback_amount;
	}

	public void setTotal_pill(double total_pill) {
		this.total_pill = total_pill;
	}

	public void setUnpayed(double unpayed) {
		this.unpayed = unpayed;
	}

}
