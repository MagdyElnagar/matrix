package com.ERPMatrix.Application.Service.purchases.pill;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;

@Service
public interface salespilldetailsServ {

	List<supliserpilldetails> changePillSupliser(supliserpill supliserpill);

	List<supliserpilldetails> delete(supliserpilldetails salespilldetails) throws Exception;

	void deleteAll(String pillid);

	List<supliserpilldetails> deleteByPillid(supliserpilldetails supliserpilldetails);

	List<supliserpilldetails> deleteRow(supliserpilldetails supliserpilldetails);

	supliserpilldetails findById(Long id);

	List<supliserpilldetails> findByPillid(String pillid);

	List<supliserpilldetails> findByProduct(String productname);

	supliserpilldetails findByProductAndPillId(String productname, String pillid);

	supliserpilldetails findByProductAndPillId(supliserpilldetails supliserpilldetails);

	Optional<supliserpilldetails> findBysupliserpilldetailsID(Long id);

	List<supliserpilldetails> getLast5Record(String productname);

	supliserpilldetails getLastRecordForProduct(String productname);

	List<supliserpilldetails> save(supliserpilldetails salespilldetails);

	List<supliserpilldetails> saveRow(supliserpilldetails supliserpilldetails) throws Exception;

	List<supliserpilldetails> updateRow(supliserpilldetails supliserpilldetails);
}
