package com.ERPMatrix.Application.Controller.finance.pill.reback;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/finance_reback/" })
@CrossOrigin(origins = "*")
public class reback_pill_Controller {

	@Autowired
	private reback_pill_service reback_service;

	@PostMapping("delete")
	public ResponseEntity<List<reback_pill>> delete(@RequestBody reback_pill reback_pill) {

		reback_service.delete(reback_pill);

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("findByClientname")
	public ResponseEntity<List<reback_pill>> findByClientname(@RequestParam String clientname) {

		List<reback_pill> pi = reback_service.findByClientname(clientname);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findReback")
	public ResponseEntity<List<reback_pill>> findByClientnameAndEntrydateGreaterThanEqual(
			@RequestParam String clientname, @RequestParam String firstDate, @RequestParam String lastDate)
			throws ParseException {
		List<reback_pill> pi = new ArrayList<reback_pill>();

		if (clientname.equals(null) || clientname.equals("") || clientname.equals("null")) {

			if (firstDate == null || firstDate.equals(null) || firstDate.equals("") || firstDate.equals("null")) {

				if (lastDate == null || lastDate.equals(null) || lastDate.equals("") || lastDate.equals("null")) {
					pi = reback_service.findAll();

				}

			} else {

				pi = reback_service.findByEntrydateGreaterThanEqual(firstDate);

			}

		} else {

			if (firstDate == null || firstDate.equals(null) || firstDate.equals("") || firstDate.equals("null")) {

				if (lastDate == null || lastDate.equals(null) || lastDate.equals("") || lastDate.equals("null")) {
					pi = reback_service.findByClientname(clientname);

				}

			} else {

				if (lastDate == null || lastDate.equals(null) || lastDate.equals("") || lastDate.equals("null")) {
					pi = reback_service.findByClientnameAndEntrydateGreaterThanEqual(clientname, firstDate);

				} else {
					pi = reback_service.findByClientnameAndEntrydateGreaterThanEqualAndEntrydateLessThanEqual(
							clientname, firstDate, lastDate);

				}

			}

		}

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findById")
	public ResponseEntity<reback_pill> findById(@RequestParam String id) {

		reback_pill pi = reback_service.findById(Long.parseLong(id));

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByPillid")
	public ResponseEntity<reback_pill> findByPillid(@RequestParam String id) {

		reback_pill pi = reback_service.findByPillid(id);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("save")
	public ResponseEntity<reback_pill> save(@RequestBody reback_pill reback_pill)
			throws NumberFormatException, HandlerException {

		reback_pill pi = reback_service.save(reback_pill);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("update")
	public ResponseEntity<reback_pill> update(@RequestBody reback_pill reback_pill) {

		reback_pill pi = reback_service.update(reback_pill);

		return new ResponseEntity<>(pi, OK);
	}

}
