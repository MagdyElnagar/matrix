package com.ERPMatrix.Application.Service.purchases.order;

import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.purchases.Order.orderdetails;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface orderdetailsServ {

	void deleteById(Long id);

	List<orderdetails> deleteByOrderid(orderdetails orderdetails) throws HandlerException;

	List<orderdetails> deleteRow(orderdetails orderdetails) throws HandlerException;

	Optional<orderdetails> findByorderdetailsID(Long id);

	List<orderdetails> findByOrderid(String orderid);

	orderdetails findProductname(String productname);

	List<orderdetails> save(orderdetails orderdetails) throws HandlerException;

	List<orderdetails> updateRow(orderdetails orderdetails);

}
