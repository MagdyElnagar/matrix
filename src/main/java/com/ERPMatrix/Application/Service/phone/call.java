package com.ERPMatrix.Application.Service.phone;

import java.net.URISyntaxException;

import com.ERPMatrix.Application.Model.Phone.call_Model;

public interface call {

	call_Model call_this(call_Model call_Model) throws URISyntaxException;

}
