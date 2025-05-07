package com.ERPMatrix.Application.Service.finance.pill;

import java.text.ParseException;
import java.util.List;

import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface pillDetailsServ {

	List<pilldetails> delete(pilldetails pilldetails) throws NumberFormatException, HandlerException, ParseException;

	List<pilldetails> findAll();

	pilldetails findById(Long id) throws HandlerException;

	List<pilldetails> findByPillid(String pillid) throws NumberFormatException, HandlerException;

	List<pilldetails> findByProduct(String product);

	List<pilldetails> findByProductname(String productname) throws HandlerException;

	pilldetails findByProductnameAndPillid(pilldetails pilldetails) throws HandlerException;

	pilldetails findByProductnameAndPillidAndPatch(String product, String pillid, String patch);

	List<pilldetails> insert(pilldetails pilldetails);

	List<pilldetails> save(pilldetails pilldetails) throws HandlerException, ParseException;

	List<pilldetails> update(pilldetails pilldetails);

}
