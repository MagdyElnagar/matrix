package com.ERPMatrix.Application.Model.finance.Accountat;

public class MonitorModel {

	private String client;
	private String delegate_name;
	private String employee_name;
	private String from;
	private String must_pay;
	private String pills_time;
	private String rank;
	private String store;
	private String to;
	private String trafic;

	public MonitorModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonitorModel(String delegate_name, String employee_name, String client, String trafic, String from,
			String must_pay, String pills_time, String rank, String store, String to) {
		this.delegate_name = delegate_name;
		this.employee_name = employee_name;
		this.client = client;
		this.trafic = trafic;
		this.from = from;
		this.must_pay = must_pay;
		this.pills_time = pills_time;
		this.rank = rank;
		this.store = store;
		this.to = to;
	}

	public String getClient() {
		return client;
	}

	public String getDelegate_name() {
		return delegate_name;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public String getFrom() {
		return from;
	}

	public String getMust_pay() {
		return must_pay;
	}

	public String getPills_time() {
		return pills_time;
	}

	public String getRank() {
		return rank;
	}

	public String getStore() {
		return store;
	}

	public String getTo() {
		return to;
	}

	public String getTrafic() {
		return trafic;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setDelegate_name(String delegate_name) {
		this.delegate_name = delegate_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setMust_pay(String must_pay) {
		this.must_pay = must_pay;
	}

	public void setPills_time(String pills_time) {
		this.pills_time = pills_time;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setTrafic(String trafic) {
		this.trafic = trafic;
	}

}
