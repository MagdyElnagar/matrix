package com.ERPMatrix.Application.Controller.admin.rank;

import static org.springframework.http.HttpStatus.OK;

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

import com.ERPMatrix.Application.Model.admin.rank.rankmodel;
import com.ERPMatrix.Application.Service.admin.rank.rankServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/rank/" })
@CrossOrigin(origins = "*")
public class rankController extends ExceptionHandling {

	private rankServ rankserv;

	@Autowired
	public rankController(rankServ rankServ) {
		this.rankserv = rankServ;
	}

	@GetMapping("findAll")
	public ResponseEntity<List<rankmodel>> findAll() {

		List<rankmodel> rank = rankserv.findAll();

		return new ResponseEntity<>(rank, OK);
	}

	@PostMapping("findByName")
	public ResponseEntity<rankmodel> findByName(@RequestParam("name") String name) {

		rankmodel rank = rankserv.findByName(name);

		return new ResponseEntity<>(rank, OK);
	}

	@PostMapping("save")
	public ResponseEntity<rankmodel> save(@RequestBody rankmodel rankmodel) throws HandlerException {

		rankmodel rank = rankserv.Save(rankmodel);

		return new ResponseEntity<>(rank, OK);
	}

	@PostMapping("delete")
	public ResponseEntity<rankmodel> updateProduct(@RequestBody rankmodel rankmodel) throws HandlerException {

		rankserv.delete(rankmodel);

		return new ResponseEntity<>(OK);
	}
}
