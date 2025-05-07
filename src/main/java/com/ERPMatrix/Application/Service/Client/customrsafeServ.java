package com.ERPMatrix.Application.Service.Client;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.finance.Accountat.customersafe;

@Service
public interface customrsafeServ {

	List<customersafe> findByClientid(String cliid);

	List<customersafe> findByClientname(String cliname);

	List<customersafe> findByEntryGreatherThanEqual(Date date);

	customersafe save(customersafe customersafe);

}
