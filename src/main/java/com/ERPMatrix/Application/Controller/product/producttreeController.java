package com.ERPMatrix.Application.Controller.product;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.product.producttree;
import com.ERPMatrix.Application.Service_implement.product.producttreeImpl;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/producttree/" })
@CrossOrigin(origins = "*")
public class producttreeController extends ExceptionHandling {

	@Autowired
	private producttreeImpl treeimpl;

	@PostMapping("addtree")
	public ResponseEntity<producttree> saveProduct(@RequestBody producttree producttree) throws HandlerException {

		producttree saveProd = treeimpl.save(producttree);

		return new ResponseEntity<>(saveProd, OK);
	}

}
