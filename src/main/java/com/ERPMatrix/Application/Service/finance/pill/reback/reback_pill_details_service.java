package com.ERPMatrix.Application.Service.finance.pill.reback;

import java.util.List;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill_details;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface reback_pill_details_service {

	List<reback_pill_details> delete(reback_pill_details reback_pill_details);

	reback_pill_details findById(String id);

	List<reback_pill_details> findByProductname(String productname);

	reback_pill_details findByProductnameAndRebackid(String productname, String rebackid);

	List<reback_pill_details> findByRebackid(String id);

	List<reback_pill_details> save(reback_pill_details reback_pill_details) throws HandlerException;

	reback_pill_details update(reback_pill_details reback_pill_details);

}
