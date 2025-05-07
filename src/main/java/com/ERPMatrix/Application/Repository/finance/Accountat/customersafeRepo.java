package com.ERPMatrix.Application.Repository.finance.Accountat;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.Accountat.customersafe;

@Repository
public interface customersafeRepo extends JpaRepository<customersafe, Long> {

	List<customersafe> findByClientid(String cliid);

	List<customersafe> findByClientname(String clientname);

	List<customersafe> findByEntryGreaterThanEqual(Date date);

}
