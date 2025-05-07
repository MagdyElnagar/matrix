package com.ERPMatrix.Application.Repository.finance.pill.reback;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;

@Repository
public interface reback_pill_repo extends JpaRepository<reback_pill, Long> {

	List<reback_pill> findByClientname(String clientname);

	List<reback_pill> findByClientnameAndEntrydateGreaterThanEqual(String clientname, Date date);

	List<reback_pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname,
			Date fdate, Date ldate);

	List<reback_pill> findByEmployeename(String employeename);

	List<reback_pill> findByEntrydateGreaterThanEqual(Date fdate);

	List<reback_pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fdate, Date ldate);

	reback_pill findByPillid(String pillid);

}
