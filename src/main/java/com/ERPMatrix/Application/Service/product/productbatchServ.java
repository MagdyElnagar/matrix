package com.ERPMatrix.Application.Service.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface productbatchServ {

	productbatch add(productbatch productbatch, supliserpilldetails supliserpilldetails) throws HandlerException;

	void deleteBatch(Long batchid) throws HandlerException;

	List<productbatch> deleteFromPill(Long id);

	List<productbatch> findByBatch(String batch);

	List<productbatch> findByDateBetweenDate(Date fDate, Date SDate);

	List<productbatch> findByExpire(Date date);

	Optional<productbatch> findById(Long id);

	product findByNameAndStatusAndStore(String name, String store) throws HandlerException;

	List<productbatch> findByPillid(String pillid);

	productbatch findByPillidAndProductname(String product, String pillid);

	List<productbatch> findByProductid(String productid);

	List<productbatch> findByProductidAndProductqoutaGreaterThan(String productid, int qouta);

	List<productbatch> findByProductname(String productname) throws HandlerException;

	List<productbatch> findByProductnameAndPillid(String product, String pillid);

	productbatch findByProductnameAndProductpriceAndBatch(String name, double price, String batch);

	List<productbatch> findByProductnameAndProductpriceAndBatchIfPatchEqualNull(String name, double price,
			String batch);

	List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThan(String Productname, double price,
			int qty);

	List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThanOrderByExpire(String proname,
			double price, int qouta);

	List<productbatch> findByProductnameAndProductqoutaGreaterThanOrderByExpire(String proname, int qouta);

	List<productbatch> findByProductnameAndStoreAndProductpriceAndProductqoutaGreaterThanOrderByExpire(
			String Productname, String sotre, double price, int qouta);

	List<productbatch> findByProductnameOrderByExpire(String proname);

	List<productbatch> getFullDetailsForProduct(String name);

	productbatch minToProduct(productbatch productbatch);

	productbatch plusToProduct(productbatch productbatch);

	productbatch save(productbatch productbatch) throws HandlerException;

	productbatch update(productbatch productbatch) throws HandlerException;

	productbatch updatePatch(productbatch productbatch);

	void updatePatchesDiscount(String proname, double price) throws HandlerException;

}
