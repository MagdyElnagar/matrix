package com.ERPMatrix.Application.Controller.supliser.rebak;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountExpiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;
import com.ERPMatrix.Application.Service.purchases.reback.rebackPillServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@RequestMapping(path = { "/", "/rebackPill/" })
@CrossOrigin(origins = "*")
public class rebackPillController extends ExceptionHandling {

	@Autowired
	private rebackPillServ rebackServ;

	@PostMapping("/addReback")
	public ResponseEntity<rebackPillModel> addReback(@RequestBody rebackPillModel rebackPillModel)
			throws AccountExpiredException {
		rebackPillModel.setEntrydate(new Date());
		System.out.println(rebackPillModel.getPrice());
		rebackPillModel rebackPil = rebackServ.save(rebackPillModel);

		return new ResponseEntity<>(rebackPil, OK);
	}

	@PostMapping("/frindBySupliserAndDate")
	public ResponseEntity<List<rebackPillModel>> frindBySupliserAndDate(@RequestParam("from") String from,
			@RequestParam("to") String to, @RequestParam("suplisername") String suplisername) throws ParseException {
		List<rebackPillModel> pills = rebackServ.findForMonitor(suplisername, from, to);
		return new ResponseEntity<>(pills, OK);

	}

}
