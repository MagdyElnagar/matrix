package com.ERPMatrix.Application.Controller.finance.pill;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Service.finance.pill.pillDetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/pillDetails/" })
@CrossOrigin(origins = "*")
public class pillDetailsController extends ExceptionHandling {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	pillDetailsServ pillDetailsServ;

	@PostMapping("addRow")
	public ResponseEntity<List<pilldetails>> addRow(@RequestBody pilldetails pilldetails)
			throws HandlerException, ParseException {
		List<pilldetails> savedOrder = pillDetailsServ.save(pilldetails);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("deleteRow")
	public ResponseEntity<List<pilldetails>> deleteRow(@RequestBody pilldetails pilldetails)
			throws HandlerException, ParseException {

		List<pilldetails> savedOrder = pillDetailsServ.delete(pilldetails);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("findByPillID")
	public ResponseEntity<List<pilldetails>> findByPillID(@RequestParam("pillid") String pillid)
			throws HandlerException, ParseException {

		List<pilldetails> savedOrder = pillDetailsServ.findByPillid(pillid);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("findByProduct")
	public ResponseEntity<List<pilldetails>> findByProduct(@RequestParam("product") String product)
			throws HandlerException, ParseException {

		List<pilldetails> savedOrder = pillDetailsServ.findByProduct(product);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("updateRow")
	public ResponseEntity<List<pilldetails>> updateRow(@RequestBody pilldetails pilldetails)
			throws HandlerException, ParseException {

		List<pilldetails> savedOrder = pillDetailsServ.update(pilldetails);

		return new ResponseEntity<>(savedOrder, OK);
	}

}
