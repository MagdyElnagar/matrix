package com.ERPMatrix.Application.Controller.supliser.pill;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpillImpl;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/Purchases/" })
@CrossOrigin(origins = "*")
public class salepillController extends ExceptionHandling {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	supliserpillImpl supliserpillImpl;

	@PostMapping("addOrder")
	public ResponseEntity<supliserpill> addNewOrder(@RequestBody supliserpill supliserpill) throws HandlerException {

		if (supliserpill.getEntrydate() == null) {

			supliserpill.setEntrydate(new Date());
		} else if (supliserpill.getEmployeename() == null || supliserpill.getSuplisername().equals("null")) {

			throw new HandlerException("بيانات الفاتروة غير مكتملة", "salepillController addNewOrder");
		}
		supliserpill savedOrder = supliserpillImpl.save(supliserpill);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("changeName")
	public ResponseEntity<List<supliserpill>> changeName(@RequestParam("id") String id,
			@RequestParam("oldName") String oldName, @RequestParam("newName") String newName) {

		supliserpillImpl.changeName(Long.decode(id), oldName, newName);

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("changePillSupliser")
	public ResponseEntity<List<supliserpill>> changePillSupliser(@RequestBody supliserpill supliserpill) {

		supliserpillImpl.changePillSupliser(supliserpill);

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("Close")
	public ResponseEntity<List<supliserpill>> Close(@RequestBody supliserpill supliserpill) {

		supliserpillImpl.close(supliserpill.getId());

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("edit")
	public ResponseEntity<supliserpill> edit(@RequestBody supliserpill supliserpill) {
		supliserpill.setLastedit(new Date());
		supliserpill order = supliserpillImpl.update(supliserpill);

		return new ResponseEntity<>(order, OK);
	}

	@GetMapping("findall")
	public ResponseEntity<List<supliserpill>> findall() {

		List<supliserpill> pills = supliserpillImpl.findAll();

		return new ResponseEntity<>(pills, OK);
	}

	@PostMapping("findAllByEntrydate")
	public ResponseEntity<List<supliserpill>> findAllByEntrydate(@RequestParam("entrydate") Date entrydate) {

		List<supliserpill> order = supliserpillImpl.findAllByEntrydate(entrydate);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findAllByEntrydateBetween")
	public ResponseEntity<List<supliserpill>> findAllByEntrydateBetween(@RequestParam("firstdate") Date firstdate,
			@RequestParam("seconddate") Date seconddate) {

		List<supliserpill> order = supliserpillImpl.findAllByEntrydateBetween(firstdate, seconddate);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByClosed")
	public ResponseEntity<List<supliserpill>> findByClosed(@RequestBody supliserpill supliserpill) {

		List<supliserpill> order = supliserpillImpl.findByClosed(supliserpill.isClosed());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByEmployeename")
	public ResponseEntity<List<supliserpill>> findByEmployeename(@RequestBody supliserpill supliserpill) {
		List<supliserpill> order = supliserpillImpl.findByEmployeename(supliserpill.getEmployeename());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findById")
	public ResponseEntity<Optional<supliserpill>> findById(@RequestBody supliserpill supliserpill) {
		System.out.println(supliserpill.getId());
		Optional<supliserpill> order = supliserpillImpl.findById(supliserpill.getId());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByPrint")
	public ResponseEntity<List<supliserpill>> findByPrint(@RequestBody supliserpill supliserpill) {

		List<supliserpill> order = supliserpillImpl.findByClosed(supliserpill.isPrint());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliserid")
	public ResponseEntity<List<supliserpill>> findBySupliserid(@RequestBody supliserpill supliserpill) {
		LOGGER.info(supliserpill.getSuplisername());

		List<supliserpill> order = supliserpillImpl.findBySupliserid(supliserpill.getSupliserid());

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliseridAndSuplisername")
	public ResponseEntity<List<supliserpill>> findBySupliseridAndSuplisername(@RequestBody supliserpill supliserpill) {
		List<supliserpill> order = supliserpillImpl.findBySupliseridAndSuplisername(supliserpill.getSupliserid(),
				supliserpill.getSuplisername());

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliserName")
	public ResponseEntity<List<supliserpill>> findBySupliserName(@RequestBody supliserpill supliserpill) {
		LOGGER.info(supliserpill.getSuplisername());

		List<supliserpill> order = supliserpillImpl.findBySuplisername(supliserpill.getSuplisername());

		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliserPillId")
	public ResponseEntity<supliserpill> findBySupliserPillId(@RequestBody supliserpill supliserpill) {

		supliserpill order = supliserpillImpl.findBySupliserpillid(supliserpill.getSupliserpillid());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findSearchOrder")
	public ResponseEntity<List<supliserpill>> findSearchOrder(@RequestParam("from") String from,
			@RequestParam("to") String to, @RequestParam("suplisername") String suplisername,
			@RequestParam("id") String id, @RequestParam("supliserid") String supliserid)
			throws ParseException, HandlerException {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat YF = new SimpleDateFormat("yyyy");
		SimpleDateFormat MF = new SimpleDateFormat("MM");
		SimpleDateFormat DF = new SimpleDateFormat("dd");

		// cheak with order id
		List<supliserpill> finder = supliserpillImpl.findSearchPill(suplisername, id, from, to);

		return new ResponseEntity<>(finder, OK);
	}

	@PostMapping("findSupliserPills")
	public ResponseEntity<List<supliserpill>> findSupliserPills(@RequestParam("from") String from,
			@RequestParam("to") String to, @RequestParam("suplisername") String suplisername)
			throws ParseException, HandlerException {

		List<supliserpill> pills = supliserpillImpl.findForMonitor(suplisername, from, to);
		return new ResponseEntity<>(pills, OK);
	}

	@PostMapping("open")
	public ResponseEntity<List<supliserpill>> open(@RequestBody supliserpill supliserpill) {

		supliserpillImpl.open(supliserpill.getId());

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("test")
	public ResponseEntity<supliserorder> test(@RequestBody supliserpill supliserpill) {
		for (int x = 0; x < 500000; x++) {
			supliserpill.setId(Long.decode(String.valueOf(x + 1)));
			supliserpill savedOrder = supliserpillImpl.save(supliserpill);
			System.out.println("Row : " + x);

		}
		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("verifyLastDiscount")
	public ResponseEntity<supliserpilldetails> verifyLastDiscount(@RequestBody String productname) {

		supliserpilldetails order = supliserpillImpl.verifyLastDiscount(productname);
		return new ResponseEntity<>(order, OK);
	}

}
