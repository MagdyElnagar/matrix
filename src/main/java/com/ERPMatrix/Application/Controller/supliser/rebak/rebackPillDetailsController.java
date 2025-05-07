package com.ERPMatrix.Application.Controller.supliser.rebak;

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

import com.ERPMatrix.Application.Model.purchases.Reback.rebakDetailsModel;
import com.ERPMatrix.Application.Service.purchases.reback.rebackPillDetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/rebackPillDetails/" })
@CrossOrigin(origins = "*")
public class rebackPillDetailsController extends ExceptionHandling {

	@Autowired
	private rebackPillDetailsServ rebakdetailsServ;

	@PostMapping("addRow")
	public ResponseEntity<rebakDetailsModel> addRow(@RequestBody rebakDetailsModel rebakDetailsModel)
			throws HandlerException {

		rebakDetailsModel row = rebakdetailsServ.save(rebakDetailsModel);

		return new ResponseEntity<>(row, OK);

	}

	@PostMapping("deleteRow")
	public ResponseEntity<List<rebakDetailsModel>> delete(@RequestBody rebakDetailsModel rebakDetailsModel)
			throws HandlerException {

		List<rebakDetailsModel> row = rebakdetailsServ.deleteRow(rebakDetailsModel);

		return new ResponseEntity<>(row, OK);

	}

	@PostMapping("findByProduct")
	public ResponseEntity<List<rebakDetailsModel>> findByProduct(@RequestParam("product") String product)
			throws HandlerException {

		List<rebakDetailsModel> rows = rebakdetailsServ.findByProduct(product);

		return new ResponseEntity<>(rows, OK);

	}

	@PostMapping("findRebackRows")
	public ResponseEntity<List<rebakDetailsModel>> findRebackRows(@RequestParam("rebackid") String rebackid)
			throws HandlerException {

		List<rebakDetailsModel> rows = rebakdetailsServ.findByRebackid(rebackid);

		return new ResponseEntity<>(rows, OK);

	}

	@PostMapping("findRebackRowsByPillId")
	public ResponseEntity<List<rebakDetailsModel>> findRebackRowsByPillId(@RequestParam("pillid") String pillid)
			throws HandlerException {

		List<rebakDetailsModel> rows = rebakdetailsServ.findByPillid(pillid);

		return new ResponseEntity<>(rows, OK);

	}

}
