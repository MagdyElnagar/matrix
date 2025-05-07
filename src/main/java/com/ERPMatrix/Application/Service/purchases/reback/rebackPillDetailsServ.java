package com.ERPMatrix.Application.Service.purchases.reback;

import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.purchases.Reback.rebakDetailsModel;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface rebackPillDetailsServ {

	List<rebakDetailsModel> deleteRow(rebakDetailsModel rebakDetailsModel) throws HandlerException;

	List<rebakDetailsModel> findAll();

	Optional<rebakDetailsModel> findById(Long id);

	List<rebakDetailsModel> findByPillid(String pillid);

	List<rebakDetailsModel> findByProduct(String productname);

	rebakDetailsModel findByProductnameAndRebackid(String productname, String Rebackid);

	List<rebakDetailsModel> findByRebackid(String Rebackid);

	rebakDetailsModel save(rebakDetailsModel rebakDetailsModel) throws HandlerException;

}
