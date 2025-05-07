package com.ERPMatrix.Application.Controller.supliser.pill;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Service.purchases.pill.salespilldetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@RequestMapping(path = { "/", "/PurchasesDetails/" })
@CrossOrigin(origins = "*")
public class salepilldetailsController extends ExceptionHandling {
	@Autowired
	private salespilldetailsServ salespilldetailsServ;

	@PostMapping("addRow")
	public ResponseEntity<List<supliserpilldetails>> addRow(@RequestBody supliserpilldetails supliserpilldetails)
			throws Exception {

		List<supliserpilldetails> order = salespilldetailsServ.saveRow(supliserpilldetails);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("deleteAll")
	public ResponseEntity<List<supliserpilldetails>> deleteAll(@RequestBody supliserpilldetails supliserpilldetails) {
		List<supliserpilldetails> order = salespilldetailsServ.deleteByPillid(supliserpilldetails);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("deleteRow")
	public ResponseEntity<List<supliserpilldetails>> deleteRow(@RequestBody supliserpilldetails supliserpilldetails)
			throws Exception {
		List<supliserpilldetails> order = salespilldetailsServ.delete(supliserpilldetails);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByOrderid")
	public ResponseEntity<List<supliserpilldetails>> findByOrderid(
			@RequestBody supliserpilldetails supliserpilldetails) {
		List<supliserpilldetails> order = salespilldetailsServ.findByPillid(supliserpilldetails.getId().toString());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByProduct")
	public ResponseEntity<List<supliserpilldetails>> findByProduct(@RequestParam("product") String product) {
		List<supliserpilldetails> order = salespilldetailsServ.findByProduct(product);

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByProductAndPillId")
	public ResponseEntity<supliserpilldetails> findByProductAndPillId(
			@RequestBody supliserpilldetails supliserpilldetails) {
		System.out.println(supliserpilldetails.getProductname());
		supliserpilldetails order = salespilldetailsServ.findByProductAndPillId(supliserpilldetails);

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findrow")
	public ResponseEntity<Optional<supliserpilldetails>> findrow(@RequestBody supliserpilldetails supliserpilldetails) {
		Optional<supliserpilldetails> order = salespilldetailsServ
				.findBysupliserpilldetailsID(supliserpilldetails.getId());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("getLast5RecordForProduct")
	public ResponseEntity<List<supliserpilldetails>> getLast5RecordForProduct(
			@RequestParam("productname") String productname) {

		List<supliserpilldetails> order = salespilldetailsServ.getLast5Record(productname);

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("getLastRecordForProduct")
	public ResponseEntity<supliserpilldetails> getLastRecordForProduct(
			@RequestParam("productname") String productname) {

		supliserpilldetails order = salespilldetailsServ.getLastRecordForProduct(productname);
		System.out.println(order.getProductname());

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("updateRow")
	public ResponseEntity<List<supliserpilldetails>> updateRow(@RequestBody supliserpilldetails supliserpilldetails) {

		List<supliserpilldetails> order = salespilldetailsServ.updateRow(supliserpilldetails);

		return new ResponseEntity<>(order, OK);
	}

}
