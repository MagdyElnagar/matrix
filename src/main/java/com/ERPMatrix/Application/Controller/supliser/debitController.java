package com.ERPMatrix.Application.Controller.supliser;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;
import com.ERPMatrix.Application.Service.purchases.supliser.depitHistoryServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@RequestMapping(path = { "/", "/supliserDebit/" })
@CrossOrigin(origins = "*")
public class debitController extends ExceptionHandling {

	@Autowired
	private depitHistoryServ dipitServ;

	@PostMapping("findByPillID")
	public ResponseEntity<List<DepitHistory>> findByPillID(@RequestBody DepitHistory DepitHistory) {
		List<DepitHistory> dep = dipitServ.findByPillid(DepitHistory.getPillid());
		return new ResponseEntity<>(dep, OK);
	}

	@PostMapping("saveDepit")
	public ResponseEntity<DepitHistory> saveDepit(@RequestBody DepitHistory DepitHistory) {
		DepitHistory dep = dipitServ.saveDepitHistory(DepitHistory);
		return new ResponseEntity<>(dep, OK);
	}

}
