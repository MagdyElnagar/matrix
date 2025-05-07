package com.ERPMatrix.Application.Controller.Client;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatClientMonitoring;
import com.ERPMatrix.Application.Service.Client.ClientServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/client/" })
@CrossOrigin(origins = "*")
public class ClientController extends ExceptionHandling {

	@Autowired
	private ClientServ clientserv;

	@PostMapping("addClient")
	public ResponseEntity<client> addClient(@RequestBody client client) throws HandlerException {

		client cc = clientserv.save(client);

		return new ResponseEntity<>(cc, OK);
	}

	@GetMapping("findAll")
	public ResponseEntity<List<client>> findAll() {

		List<client> client = clientserv.findAll();

		return new ResponseEntity<>(client, OK);
	}

	@GetMapping("findAllFinanceMonitor")
	public ResponseEntity<List<client>> findAllFinanceMonitor() {

		List<client> client = clientserv.findAllFinanceMonitor();

		return new ResponseEntity<>(client, OK);
	}
	
	@PostMapping("AccountatClientMonitoring")
	public ResponseEntity<AccountatClientMonitoring> AccountatClientMonitoring(
			@RequestParam("clientname") String suplisername, @RequestParam("datefrom") String datefrom,
			@RequestParam("dateto") String dateto) throws HandlerException, ParseException {

		AccountatClientMonitoring Monitor = clientserv.clientAccountMoitor(suplisername, datefrom, dateto);

		return new ResponseEntity<>(Monitor, OK);
	}
	
	

	@PostMapping("findByName")
	public ResponseEntity<client> findByName(@RequestParam("clientname") String clientname) {
		client client = clientserv.findByName(clientname);

		return new ResponseEntity<>(client, OK);
	}

}
