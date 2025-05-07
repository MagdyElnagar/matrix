package com.ERPMatrix.Application.Model.chat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Chat_MSG_Model")

public class msgModel {

	private String chatid;
	@Id
	private Long id;
	private String message;
	private Date msgtime;
	private String reciver;
	private String sender;

	public msgModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public msgModel(Long id, String sender, String reciver, String message, String chatid, Date msgtime) {
		super();
		this.id = id;
		this.sender = sender;
		this.reciver = reciver;
		this.message = message;
		this.chatid = chatid;
		this.msgtime = msgtime;
	}

	public String getChatid() {
		return chatid;
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getMsgtime() {
		return msgtime;
	}

	public String getReciver() {
		return reciver;
	}

	public String getSender() {
		return sender;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMsgtime(Date msgtime) {
		this.msgtime = msgtime;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
