package com.ERPMatrix.Application.Controller.finance.acountat;

import static com.ERPMatrix.Application.Constant.statsicValues.START_TIME;
import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.Accountat.incomepayment;
import com.ERPMatrix.Application.Model.finance.Accountat.outcomepayment;
import com.ERPMatrix.Application.Service.finance.Acountat.incomepaymentServ;
import com.ERPMatrix.Application.Service.finance.Acountat.outcomepaymentServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@Configuration
@RequestMapping(path = { "/", "/payment/" })
@CrossOrigin(origins = "*")
public class payment extends ExceptionHandling {

	private incomepaymentServ incomeServ;
	private outcomepaymentServ outcomeServ;

	@Autowired
	public payment(incomepaymentServ incomeServ, outcomepaymentServ outcomeServ) {
		super();
		this.incomeServ = incomeServ;
		this.outcomeServ = outcomeServ;
	}

	@PostMapping("addIncome")
	public ResponseEntity<incomepayment> addIncome(@RequestBody incomepayment incomepayment) {
		incomepayment.setPayeddate(new Date());
		incomepayment payments = incomeServ.save(incomepayment);

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("addOutCome")
	public ResponseEntity<outcomepayment> addOutCome(@RequestBody outcomepayment outcomepayment) {
		outcomepayment.setPayeddate(new Date());
		outcomepayment payments = outcomeServ.save(outcomepayment);

		return new ResponseEntity<>(payments, OK);
	}

	@GetMapping("findAllIncome")
	public ResponseEntity<List<incomepayment>> findAllIncome() {

		List<incomepayment> payments = incomeServ.findAll();

		return new ResponseEntity<>(payments, OK);
	}

	@GetMapping("findAllOutcome")
	public ResponseEntity<List<outcomepayment>> findAllOutcome() {

		List<outcomepayment> payments = outcomeServ.findAll();

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findIncomeByclientid")
	public ResponseEntity<List<incomepayment>> findIncomeByclientid(@RequestParam String clientid)
			throws ParseException {

		List<incomepayment> payments = incomeServ.findByClientid(clientid);

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findIncomeByDate")
	public ResponseEntity<List<incomepayment>> findIncomeByDate(@RequestParam String date) throws ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");
		date = date + START_TIME;
		List<incomepayment> payments = incomeServ.findByPayeddateGreaterThanEqual(SDF.parse(date));

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findIncomeBypillid")
	public ResponseEntity<incomepayment> findIncomeBypillid(@RequestParam String pillid) throws ParseException {

		incomepayment payments = incomeServ.findByPillid(pillid);

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findOutcomeByclientid")
	public ResponseEntity<List<outcomepayment>> findOutcomeByclientid(@RequestParam String clientid)
			throws ParseException {

		List<outcomepayment> payments = outcomeServ.findByClientid(clientid);

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findOutcomeByDate")
	public ResponseEntity<List<outcomepayment>> findOutcomeByDate(@RequestParam String date) throws ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");
		date = date + START_TIME;
		List<outcomepayment> payments = outcomeServ.findByPayeddateGreaterThanEqual(SDF.parse(date));

		return new ResponseEntity<>(payments, OK);
	}

	@PostMapping("findOutcomeBypillid")
	public ResponseEntity<outcomepayment> findOutcomeBypillid(@RequestParam String pillid) throws ParseException {

		outcomepayment payments = outcomeServ.findByPillid(pillid);

		return new ResponseEntity<>(payments, OK);
	}

}
