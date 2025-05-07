package com.ERPMatrix.Application.Controller.supliser.order;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.purchases.Order.supliserorder;
import com.ERPMatrix.Application.Service.purchases.order.supliserorderServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@RequestMapping(path = { "/", "/salesorder/" })
@CrossOrigin(origins = "*")
public class orderPillControler extends ExceptionHandling {

	@Autowired
	supliserorderServ supliserorderServ;

	@PostMapping("addOrder")
	public ResponseEntity<supliserorder> addNewOrder(@RequestBody supliserorder supliserorder)
			throws ParseException, HandlerException {
		if (supliserorder.getSupliserpillid() == null) {
			supliserorder.setSupliserpillid("0");
		}
		supliserorder.setDateofcoming(new Date());

		supliserorder.setEntrydate(new Date());

		supliserorder savedOrder = supliserorderServ.save(supliserorder);

		return new ResponseEntity<>(savedOrder, OK);
	}

	@PostMapping("closeOrder")
	public ResponseEntity<supliserorder> closeOrder(@RequestBody supliserorder supliserorder) throws HandlerException {
		if (supliserorder.getId() == null || supliserorder.getSuplisername() == null) {
			throw new HandlerException("هذه البيانات غير صحيحة", "orderPillControler closeOrder");
		} else {
			supliserorder.setClosed(true);
			supliserorderServ.save(supliserorder);
		}
		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("converttopill")
	public ResponseEntity<supliserorder> converttopill(@RequestBody supliserorder supliserorder) {

		supliserorderServ.convertopill(supliserorder);

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("edit")
	public ResponseEntity<supliserorder> edit(@RequestBody supliserorder supliserorder) {

		supliserorder order = supliserorderServ.update(supliserorder);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findAllByEntrydate")
	public ResponseEntity<List<supliserorder>> findAllByEntrydate(@RequestParam("entrydate") Date entrydate) {

		List<supliserorder> order = supliserorderServ.findAllByEntrydate(entrydate);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findAllByEntrydateBetween")
	public ResponseEntity<List<supliserorder>> findAllByEntrydateBetween(@RequestParam("firstdate") Date firstdate,
			@RequestParam("seconddate") Date seconddate) {

		List<supliserorder> order = supliserorderServ.findAllByEntrydateBetween(firstdate, seconddate);
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByClosed")
	public ResponseEntity<List<supliserorder>> findByClosed(@RequestBody supliserorder supliserorder) {

		List<supliserorder> order = supliserorderServ.findByClosed(supliserorder.isClosed());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByEmployeename")
	public ResponseEntity<List<supliserorder>> findByEmployeename(@RequestBody supliserorder supliserorder) {
		List<supliserorder> order = supliserorderServ.findByEmployeename(supliserorder.getEmployeename());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findById")
	public ResponseEntity<supliserorder> findById(@RequestBody supliserorder supliserorder) {

		supliserorder order = supliserorderServ.findById(supliserorder.getId());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findByPrint")
	public ResponseEntity<List<supliserorder>> findByPrint(@RequestBody supliserorder supliserorder) {

		List<supliserorder> order = supliserorderServ.findByClosed(supliserorder.isPrint());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliserid")
	public ResponseEntity<List<supliserorder>> findBySupliserid(@RequestBody supliserorder supliserorder) {
		List<supliserorder> order = supliserorderServ.findBySupliserid(supliserorder.getSupliserid());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliseridAndSuplisername")
	public ResponseEntity<List<supliserorder>> findBySupliseridAndSuplisername(
			@RequestBody supliserorder supliserorder) {
		List<supliserorder> order = supliserorderServ.findBySupliseridAndSuplisername(supliserorder.getSupliserid(),
				supliserorder.getSuplisername());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findBySupliserPillId")
	public ResponseEntity<supliserorder> findBySupliserPillId(@RequestBody supliserorder supliserorder) {

		supliserorder order = supliserorderServ.findBySupliserpillid(supliserorder.getSupliserpillid());
		return new ResponseEntity<>(order, OK);
	}

	@PostMapping("findSearchOrder")
	public ResponseEntity<List<supliserorder>> findSearchOrder(@RequestParam("from") String from,
			@RequestParam("to") String to, @RequestParam("suplisername") String suplisername,
			@RequestParam("id") String id, @RequestParam("supliserid") String supliserid)
			throws ParseException, HandlerException {
		List<supliserorder> order = supliserorderServ.findSearchOrder(suplisername, from, to);
		/*
		 * SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat
		 * YF = new SimpleDateFormat("yyyy"); SimpleDateFormat MF = new
		 * SimpleDateFormat("MM"); SimpleDateFormat DF = new SimpleDateFormat("dd");
		 *
		 * // cheak with order id if (id.equals(null) || id.equals("null") ||
		 * id.equals("") || id.equals(" ")) { // order id equals null // cheak supliser
		 * name System.out.println("order id equals null /  cheak supliser name");
		 *
		 * if (suplisername.equals(null) || suplisername.equals("id.equals(null)") ||
		 * suplisername.equals("") || suplisername.equals(" ")) {
		 * System.out.println("suplliser name equals null / cheak date");
		 *
		 * // suplliser name equals null // cheak date if (from.equals(null) ||
		 * from.equals("null") || from.equals("") || from.equals(" ")) {
		 * System.out.println("find All because date is null");
		 *
		 * // find All List<supliserorder> order = supliserorderServ.findAll(); return
		 * new ResponseEntity<>(order, OK); } else {
		 * System.out.println("cheak date to");
		 *
		 * // cheak date to if (to.equals(null) || to.equals("null") || to.equals("") ||
		 * to.equals(" ")) { // find from : end System.out.println("find from : end");
		 * from = from + START_TIME; List<supliserorder> order =
		 * supliserorderServ.findByEntrydateGreaterThanEqual(SDF.parse(from)); return
		 * new ResponseEntity<>(order, OK);
		 *
		 * } else { // find from to
		 *
		 * System.out.println("find from to"); from = from + START_TIME; to = to +
		 * END_TIME; System.out.println(from);
		 *
		 * System.out.println(to);
		 *
		 * List<supliserorder> order =
		 * supliserorderServ.findAllByEntrydateBetween(SDF.parse(from), SDF.parse(to));
		 * return new ResponseEntity<>(order, OK); }
		 *
		 * }
		 *
		 * } else { // suplliser name not null // cheak date from
		 * System.out.println("suplliser name not null / cheak date from");
		 *
		 * if (from.equals(null) || from.equals("null") || from.equals("") ||
		 * from.equals(" ")) { System.out.println("find by suplliser name");
		 *
		 * // find by suplliser name List<supliserorder> order =
		 * supliserorderServ.findBySuplisername(suplisername); return new
		 * ResponseEntity<>(order, OK);
		 *
		 * } else {
		 *
		 * // cheak date to System.out.println(" cheak date to");
		 *
		 * }
		 *
		 * }
		 *
		 * } else { // order id not null // find by order id
		 * System.out.println("find by order id"); Optional<supliserorder> order =
		 * Optional.ofNullable(supliserorderServ.findById(Long.decode(id))
		 * .orElseThrow(() -> new HandlerException("رقم الفاتورة غير صحيح")));
		 *
		 * List<supliserorder> or = new ArrayList<supliserorder>(); or.add(order.get());
		 * return new ResponseEntity<>(or, OK);
		 *
		 * }
		 */
		return new ResponseEntity<>(order, OK);
	}

}
