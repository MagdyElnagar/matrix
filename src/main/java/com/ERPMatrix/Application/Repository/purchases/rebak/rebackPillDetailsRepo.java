package com.ERPMatrix.Application.Repository.purchases.rebak;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Reback.rebakDetailsModel;

@Repository
public interface rebackPillDetailsRepo extends JpaRepository<rebakDetailsModel, Long> {

	List<rebakDetailsModel> findByProductname(String productname);

	rebakDetailsModel findByProductnameAndRebackid(String productname, String Rebackid);

	List<rebakDetailsModel> findByRebackid(String Rebackid);

}
