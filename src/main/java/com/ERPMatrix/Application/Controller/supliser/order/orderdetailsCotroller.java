package com.ERPMatrix.Application.Controller.supliser.order;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Order.orderdetails;
import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.Service.purchases.order.orderdetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/orderdetailsCotroller/" })
@CrossOrigin(origins = "*")
public class orderdetailsCotroller extends ExceptionHandling {

	@Autowired
	private orderdetailsServ orderdetailsServ;

	@PostMapping("addRow")
	public ResponseEntity<List<orderdetails>> addRow(@RequestBody orderdetails orderdetails) throws HandlerException {

		List<orderdetails> order = orderdetailsServ.save(orderdetails);

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("deleteAll")
	public ResponseEntity<List<orderdetails>> deleteAll(@RequestBody orderdetails orderdetails)
			throws HandlerException {
		List<orderdetails> order = orderdetailsServ.deleteByOrderid(orderdetails);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("deleteRow")
	public ResponseEntity<List<orderdetails>> deleteRow(@RequestBody orderdetails orderdetails)
			throws HandlerException {
		List<orderdetails> order = orderdetailsServ.deleteRow(orderdetails);

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByOrderid")
	public ResponseEntity<List<orderdetails>> findByOrderid(@RequestBody supliserorder supliserorder) {
		List<orderdetails> order = orderdetailsServ.findByOrderid(supliserorder.getId().toString());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findrow")
	public ResponseEntity<Optional<orderdetails>> findrow(@RequestBody orderdetails orderdetails) {
		Optional<orderdetails> order = orderdetailsServ.findByorderdetailsID(orderdetails.getId());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("updateRow")
	public ResponseEntity<List<orderdetails>> updateRow(@RequestBody orderdetails orderdetails) {

		List<orderdetails> order = orderdetailsServ.updateRow(orderdetails);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("verifyComingProductQouta")
	public ResponseEntity<orderdetails> verifyComingProductQouta(@RequestBody String productname) {

		orderdetails order = orderdetailsServ.findProductname(productname);
		return new ResponseEntity<>(order, OK);
	}

}
