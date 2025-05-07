package com.ERPMatrix.Application.Repository.finance.pill.reback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill_details;

@Repository
public interface reback_pill_details_repo extends JpaRepository<reback_pill_details, Long> {

	List<reback_pill_details> findByProduct(String productname);

	reback_pill_details findByProductAndRebackid(String productname, String rebackid);

	List<reback_pill_details> findByRebackid(String id);

	reback_pill_details findByRebackidAndProduct(String rebackid, String product);

}
