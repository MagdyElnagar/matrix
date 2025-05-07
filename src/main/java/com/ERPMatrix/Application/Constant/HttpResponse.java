package com.ERPMatrix.Application.Constant;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HttpResponse {

	// 200 > 299 sucsess , 400 > 499 user error , 500 > 599 server error

	private int httpStatusCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss" , timezone = "Africa/Cairo")
	private Date timstamp;
	private HttpStatus httpstatus;
	private String reason;
	private String message;

	public HttpResponse() {
	}

	public HttpResponse(int httpStatusCode, HttpStatus httpstatus, String reason, String message) {
		this.timstamp = new Date();

		this.httpStatusCode = httpStatusCode;
		this.httpstatus = httpstatus;
		this.reason = reason;
		this.message = message;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimstamp() {
		return timstamp;
	}

	public void setTimstamp(Date timstamp) {
		this.timstamp = timstamp;
	}
	
	

}
