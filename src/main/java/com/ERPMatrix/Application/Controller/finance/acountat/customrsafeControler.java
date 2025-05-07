package com.ERPMatrix.Application.Controller.finance.acountat;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.Accountat.customersafe;
import com.ERPMatrix.Application.Service.Client.customrsafeServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/customrsafe/" })
@CrossOrigin(origins = "*")
public class customrsafeControler extends ExceptionHandling {

	@Autowired
	private customrsafeServ csServ;

	@PostMapping("addClientCS")
	public ResponseEntity<customersafe> addClientCS(@RequestBody customersafe customersafe) throws HandlerException {

		customersafe cc = csServ.save(customersafe);

		return new ResponseEntity<>(cc, OK);
	}

	@PostMapping("findByClientname")
	public ResponseEntity<List<customersafe>> findByClientname(@RequestParam("clientname") String clientname) {
		List<customersafe> client = csServ.findByClientname(clientname);

		return new ResponseEntity<>(client, OK);
	}

}
