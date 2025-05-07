package com.ERPMatrix.Application.Model.User;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_pc_information")
public class UserPcInformation {

	private Date date;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ip;
	private String newtworkid;
	private String pcname;
	private String token;
	private String username;

	public UserPcInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPcInformation(Date date, Long id, String ip, String newtworkid, String token, String username,
			String pcname) {
		super();
		this.date = date;
		this.id = id;
		this.ip = ip;
		this.newtworkid = newtworkid;
		this.token = token;
		this.username = username;
		this.pcname = pcname;
	}

	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getNewtworkid() {
		return newtworkid;
	}

	public String getPcname() {
		return pcname;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setNewtworkid(String newtworkid) {
		this.newtworkid = newtworkid;
	}

	public void setPcname(String pcname) {
		this.pcname = pcname;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
