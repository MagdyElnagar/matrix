package com.ERPMatrix.Application.Service_implement.phone;

import java.net.URISyntaxException;

import com.ERPMatrix.Application.Model.Phone.call_Model;
import com.ERPMatrix.Application.Service.phone.call;

public class call_impl implements call {

	@Override
	public call_Model call_this(call_Model call_Model) throws URISyntaxException {

		/*
		 * Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 * 
		 * Call call = Call.creator(new PhoneNumber(MY_PHONE_NUMBER), new
		 * PhoneNumber("01119811130"), new
		 * URI("http://demo.twilio.com/docs/voice.xml")).create();
		 */

		return null;
	}

}
