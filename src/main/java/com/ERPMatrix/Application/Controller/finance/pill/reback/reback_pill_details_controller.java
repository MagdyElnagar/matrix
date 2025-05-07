package com.ERPMatrix.Application.Controller.finance.pill.reback;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill_details;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_details_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/finance_reback_detials/" })
@CrossOrigin(origins = "*")
public class reback_pill_details_controller {
	@Autowired
	private reback_pill_details_service reback_detials_service;

	@PostMapping("delete")
	public ResponseEntity<List<reback_pill_details>> delete(@RequestBody reback_pill_details reback_pill_details) {

		List<reback_pill_details> pi = reback_detials_service.delete(reback_pill_details);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByid")
	public ResponseEntity<reback_pill_details> findByid(@RequestParam String id) {

		reback_pill_details pi = reback_detials_service.findById(id);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByProductname")
	public ResponseEntity<List<reback_pill_details>> findByProductname(@RequestParam("product") String productname) {

		List<reback_pill_details> pi = reback_detials_service.findByProductname(productname);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByRebackid")
	public ResponseEntity<List<reback_pill_details>> findByRebackid(@RequestParam String id) {

		List<reback_pill_details> pi = reback_detials_service.findByRebackid(id);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("save")
	public ResponseEntity<List<reback_pill_details>> save(@RequestBody reback_pill_details reback_pill_details)
			throws HandlerException {

		List<reback_pill_details> pi = reback_detials_service.save(reback_pill_details);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("update")
	public ResponseEntity<reback_pill_details> update(@RequestBody reback_pill_details reback_pill_details) {

		reback_pill_details pi = reback_detials_service.update(reback_pill_details);

		return new ResponseEntity<>(pi, OK);
	}

}
