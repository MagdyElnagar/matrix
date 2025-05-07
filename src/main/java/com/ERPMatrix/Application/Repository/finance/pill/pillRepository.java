package com.ERPMatrix.Application.Repository.finance.pill;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.pill.pill;

@Repository
public interface pillRepository extends JpaRepository<pill, Long> {

	List<pill> findAllByEntrydateBetween(Date fDate, Date sDate);

	List<pill> findByClientname(String clientname);

	List<pill> findByClientnameAndEntrydateGreaterThanEqual(String clinetname, Date date);

	List<pill> findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(String clientname, Date fDate,
			Date sDate);

	List<pill> findByClose(boolean colsed);

	List<pill> findByCloseAndEntrydateGreaterThanEqual(boolean close, Date date);

	List<pill> findByDelegate(String delegate);

	List<pill> findByEmployee(String employee);

	List<pill> findByEntrydateGreaterThanEqual(Date date);

	List<pill> findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(Date fDate, Date sDate);

	List<pill> findByPrint(boolean print);

	List<pill> findByPrintAndEntrydateGreaterThanEqual(boolean print, Date date);

	List<pill> findByTraficline(String trafic);

	List<pill> findByTraficlineAndAndEntrydateGreaterThanEqual(String trafic, Date date);

	List<pill> findByClientnameAndEntrydateBetween(String clientname, Date fDate, Date sDate);

}
