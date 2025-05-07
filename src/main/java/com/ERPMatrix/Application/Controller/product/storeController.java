package com.ERPMatrix.Application.Controller.product;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.product.store;
import com.ERPMatrix.Application.Service.product.storeServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/store/" })
@CrossOrigin(origins = "*")
public class storeController extends ExceptionHandling {

	@Autowired
	private storeServ storeServ;

	@PostMapping("addStore")
	public ResponseEntity<store> addStore(@RequestBody store store) throws HandlerException {
		store Stores = storeServ.save(store);
		return new ResponseEntity<>(Stores, OK);

	}

	@PostMapping("deleteStore")
	public ResponseEntity<store> deleteStore(@RequestBody store store) {
		storeServ.deletestore(store);
		return new ResponseEntity<>(OK);

	}

	@GetMapping("findAll")
	public ResponseEntity<List<store>> findAll() {
		List<store> Stores = storeServ.findAll();
		return new ResponseEntity<>(Stores, OK);

	}

	@PostMapping("findStore")
	public ResponseEntity<store> findStore(@RequestBody store store) {
		store Stores = storeServ.findByName(store.getName());
		return new ResponseEntity<>(Stores, OK);

	}

}
