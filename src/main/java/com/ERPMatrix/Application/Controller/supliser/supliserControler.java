package com.ERPMatrix.Application.Controller.supliser;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatSaleMonitoring;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Service.purchases.supliser.supliserServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/supliser/" })
@CrossOrigin(origins = "*")
public class supliserControler extends ExceptionHandling {

	private supliserServ supliserServ;

	@Autowired
	public supliserControler(supliserServ supliserServ) {
		super();
		this.supliserServ = supliserServ;
	}

	@PostMapping("findActiveSup")
	public ResponseEntity<List<supliser>> findActiveSup(@RequestBody supliser supliser) {

		List<supliser> sup = supliserServ.findByStatus(supliser.isStatus());

		return new ResponseEntity<>(sup, OK);
	}

	@PostMapping("findAllSup")
	public ResponseEntity<List<supliser>> findAll() {
		List<supliser> AllSup = supliserServ.findAll();
		return new ResponseEntity<>(AllSup, OK);
	}

	@PostMapping("findSup")
	public ResponseEntity<supliser> findSup(@RequestBody supliser supliser) {
		supliser sup = supliserServ.findSup(supliser);
		return new ResponseEntity<>(sup, OK);
	}

	@PostMapping("savesupliser")
	public ResponseEntity<supliser> savesupliser(@RequestBody supliser supliser) throws HandlerException {
		supliser.setStatus(true);
		supliser sup = supliserServ.savesupliser(supliser);
		return new ResponseEntity<>(sup, OK);
	}

	@PostMapping("supliserAccountMoitor")
	public ResponseEntity<AccountatSaleMonitoring> supliserAccountMoitor(
			@RequestParam("suplisername") String suplisername, @RequestParam("datefrom") String datefrom,
			@RequestParam("dateto") String dateto) throws HandlerException, ParseException {

		AccountatSaleMonitoring Monitor = supliserServ.supliserAccountMoitor(suplisername, datefrom, dateto);

		return new ResponseEntity<>(Monitor, OK);
	}

	@PostMapping("updatesupliser")
	public ResponseEntity<supliser> updatesupliser(@RequestBody supliser supliser) {
		supliser sup = supliserServ.updatesupliser(supliser);
		return new ResponseEntity<>(sup, OK);
	}

}
