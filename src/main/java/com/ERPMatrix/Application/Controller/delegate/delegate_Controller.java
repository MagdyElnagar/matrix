package com.ERPMatrix.Application.Controller.delegate;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.delegate.delegate;
import com.ERPMatrix.Application.Service.delegate.delegate_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/delegate" })
@CrossOrigin(origins = "*")
public class delegate_Controller {

	@Autowired
	private delegate_service delegate_serv;

	@PostMapping("/delete")
	public ResponseEntity<delegate> delete(@RequestBody delegate delegate) {

		delegate saved = delegate_serv.delete(delegate);

		return new ResponseEntity<>(saved, OK);

	}

	@GetMapping("/findAll")
	public ResponseEntity<List<delegate>> findAll() {

		List<delegate> delegate = delegate_serv.findAll();

		return new ResponseEntity<>(delegate, OK);

	}

	@PostMapping("/findByCity/{Gov}")
	public ResponseEntity<delegate> findByCity(@PathVariable String Gov) {

		delegate delegate = delegate_serv.findByGov(Gov);

		return new ResponseEntity<>(delegate, OK);

	}

	@PostMapping("/findByName/{name}")
	public ResponseEntity<delegate> findByName(@PathVariable String name) {

		delegate delegate = delegate_serv.findByName(name);

		return new ResponseEntity<>(delegate, OK);

	}

	@PostMapping("/findByTraficline/{traficline}")
	public ResponseEntity<delegate> findByTraficline(@PathVariable String traficline) {

		delegate delegate = delegate_serv.findByTraficline(traficline);

		return new ResponseEntity<>(delegate, OK);

	}

	@PostMapping("/save")
	public ResponseEntity<delegate> save(@RequestBody delegate delegate) throws HandlerException {

		delegate saved = delegate_serv.save(delegate);

		return new ResponseEntity<>(saved, OK);

	}

	@PostMapping("/update")
	public ResponseEntity<delegate> update(@RequestBody delegate delegate) {

		delegate saved = delegate_serv.update(delegate);

		return new ResponseEntity<>(saved, OK);

	}

}
