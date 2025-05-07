package com.ERPMatrix.Application.Service.Client;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatClientMonitoring;
import com.ERPMatrix.Application.exception.domain.HandlerException;


@Service
public interface ClientServ {

	client edit(client client, double amountafterdis, char oper);

	List<client> findAll();

	List<client> findAllFinanceMonitor();

	client findByName(String name);

	client insert(client client);

	client save(client client) throws HandlerException;
	
	AccountatClientMonitoring clientAccountMoitor(String clientname, String datefrom, String dateto)
			throws HandlerException, ParseException;

}
