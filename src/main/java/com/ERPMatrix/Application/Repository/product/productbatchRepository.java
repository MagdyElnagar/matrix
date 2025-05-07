package com.ERPMatrix.Application.Repository.product;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.product.productbatch;

@Repository
public interface productbatchRepository extends JpaRepository<productbatch, Long> {

	List<productbatch> findByBatch(String batch);

	List<productbatch> findByEntrydateBetween(Date startDate, Date endDate);

	List<productbatch> findByEntrydateLessThanEqual(Date date);

	List<productbatch> findByPillid(String pillid);

	productbatch findByPillidAndProductname(String product, String pillid);

	List<productbatch> findByProductid(String productid);

	List<productbatch> findByProductidAndProductqoutaGreaterThan(String productid, int qouta);

	List<productbatch> findByProductname(String Productname);

	productbatch findByProductnameAndBatch(String Productname, String batch);

	productbatch findByProductnameAndBatchAndPillid(String Productname, String batch, String pillid);

	List<productbatch> findByProductnameAndPillid(String product, String pillid);

	productbatch findByProductnameAndProductpriceAndBatch(String name, double price, String batch);

	List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThan(String Productname, double price,
			int qty);

	List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThanOrderByExpire(String proname,
			double price, int qouta);

	List<productbatch> findByProductnameAndProductqoutaGreaterThanOrderByExpire(String Productname, int qouta);

	List<productbatch> findByProductnameAndStoreAndProductpriceAndProductqoutaGreaterThanOrderByExpire(
			String Productname, String sotre, double price, int qouta);

	List<productbatch> findByProductnameAndStoreAndProductqoutaGreaterThan(String productname, String store, int qouta);

	List<productbatch> findByProductnameOrderByExpire(String Productname);

	List<productbatch> findByProductpriceAndProductnameAndBatch(double price, String name, String batch);

}
