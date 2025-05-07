package com.ERPMatrix.Application.Controller.admin;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.admin.companyinfo;
import com.ERPMatrix.Application.Service_implement.admin.companyinfoImpl;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@RequestMapping(path = { "/", "/compinfo/" })
@CrossOrigin(origins = "*")
public class compinfoController extends ExceptionHandling {

	@Autowired
	private companyinfoImpl comImpl;

	@PostMapping("find")
	public ResponseEntity<companyinfo> find(@RequestBody companyinfo companyinfo) {
		companyinfo co = comImpl.findById(companyinfo.getId());
		return new ResponseEntity<>(co, OK);
	}

	@PostMapping("update")
	public ResponseEntity<companyinfo> update(@RequestBody companyinfo companyinfo) {
		companyinfo co = comImpl.update(companyinfo);
		return new ResponseEntity<>(co, OK);
	}

}
