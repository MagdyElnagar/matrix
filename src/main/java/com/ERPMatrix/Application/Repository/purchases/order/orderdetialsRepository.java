package com.ERPMatrix.Application.Repository.purchases.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Order.orderdetails;

import java.util.List;

@Repository
public interface orderdetialsRepository extends JpaRepository<orderdetails, Long> {

	List<orderdetails> findByOrderid(String orderid);

	List<orderdetails> deleteByOrderid(String orderid);

	List<orderdetails> removeByOrderid(String orderid);

	List<orderdetails> findByProductname(String productname);

	orderdetails findByOrderidAndProductname(String orderid, String productname);

	orderdetails findByOrderidAndId(String orderid, Long id);

}
