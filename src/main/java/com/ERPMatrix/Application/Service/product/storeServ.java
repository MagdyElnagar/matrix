package com.ERPMatrix.Application.Service.product;

import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.product.store;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface storeServ {
	
	
	
	Optional<store> findById(Long id);
	store findByName(String name);
	List<store> findAll();
	List<store> findByProductid(String productid);
	List<store> findByProductname(String Productname);
	store save(store store) throws HandlerException ;
	void deletestore(store store) ;
	

}
