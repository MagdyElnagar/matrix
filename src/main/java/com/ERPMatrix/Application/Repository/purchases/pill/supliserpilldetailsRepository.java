package com.ERPMatrix.Application.Repository.purchases.pill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;

@Repository
public interface supliserpilldetailsRepository extends JpaRepository<supliserpilldetails, Long> {

	supliserpilldetails deleteByPillid(String pillid);

	@Override
	List<supliserpilldetails> findAll();

	List<supliserpilldetails> findByPillid(String pillid);

	supliserpilldetails findByPillidAndId(String pillid, Long id);

	supliserpilldetails findByPillidAndProductname(String pillid, String productname);

	List<supliserpilldetails> findByProductname(String productname);

	supliserpilldetails findTop1OrderByProductname(String productname);

	List<supliserpilldetails> findTop5OrderByProductname(String productname);

}
