package com.ERPMatrix.Application.Repository.finance.Accountat;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.Accountat.incomepayment;

@Repository
public interface incomepaymentRepo extends JpaRepository<incomepayment, Long> {

	List<incomepayment> findByClientid(String clientid);

	List<incomepayment> findByPayeddateGreaterThanEqual(Date date);

	incomepayment findByPillid(String pillid);

}
