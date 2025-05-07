package com.ERPMatrix.Application.Model.chat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Chat_Model")
public class chatModel {

	@Id

	private Long id;
	private boolean isactive;
	private Date lastseen;
	private String reciver;
	private String sender;
	private String username;

	public chatModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public chatModel(Long id, boolean isactive, Date lastseen, String reciver, String sender, String username) {
		super();
		this.id = id;
		this.isactive = isactive;
		this.lastseen = lastseen;
		this.reciver = reciver;
		this.sender = sender;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public Date getLastseen() {
		return lastseen;
	}

	public String getReciver() {
		return reciver;
	}

	public String getSender() {
		return sender;
	}

	public String getUsername() {
		return username;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public void setLastseen(Date lastseen) {
		this.lastseen = lastseen;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
