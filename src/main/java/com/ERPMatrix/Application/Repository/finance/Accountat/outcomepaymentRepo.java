package com.ERPMatrix.Application.Repository.finance.Accountat;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.Accountat.outcomepayment;

@Repository
public interface outcomepaymentRepo extends JpaRepository<outcomepayment, Long> {

	List<outcomepayment> findByClientid(String clientid);

	List<outcomepayment> findByPayeddateGreaterThanEqual(Date date);

	outcomepayment findByPillid(String pillid);

}
