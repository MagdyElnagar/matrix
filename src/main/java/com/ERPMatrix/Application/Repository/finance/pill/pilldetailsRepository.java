package com.ERPMatrix.Application.Repository.finance.pill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.pill.pilldetails;

@Repository

public interface pilldetailsRepository extends JpaRepository<pilldetails, Long> {

	List<pilldetails> findByPillid(String pillid);

	List<pilldetails> findByPillidOrderByCompanyAsc(String pillid);

	List<pilldetails> findByPillidOrderByProductnameAsc(String pillid);

	List<pilldetails> findByProductname(String productname);

	pilldetails findByProductnameAndPillid(String productname, String pillid);

	pilldetails findByProductnameAndPillidAndPatch(String product, String pillid, String patch);
}
