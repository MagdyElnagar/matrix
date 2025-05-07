package com.ERPMatrix.Application.Controller.finance.pill;

import static org.springframework.http.HttpStatus.OK;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
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

import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Service.finance.pill.pillServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/pill/" })
@CrossOrigin(origins = "*")
public class pillController extends ExceptionHandling {

	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private pillServ pillServ;

	@PostMapping("addPill")
	public ResponseEntity<pill> addPill(@RequestBody pill pill) throws HandlerException, ParseException {

		if (pill.getEntrydate() == null || pill.getLastedit() == null) {

			pill.setEntrydate(new Date());
			pill.setLastedit(new Date());

			pill pi = pillServ.save(pill);

			return new ResponseEntity<>(pi, OK);

		} else {

			pill pi = pillServ.save(pill);

			return new ResponseEntity<>(pi, OK);

		}

	}

	@GetMapping("findAll")
	public ResponseEntity<List<pill>> findAll() {
		List<pill> findAll = pillServ.findAll();
		return new ResponseEntity<>(findAll, OK);

	}

	@PostMapping("findByClientname")
	public ResponseEntity<List<pill>> findByClientname(@RequestParam String Clientname) throws HandlerException {

		List<pill> pi = pillServ.findByClientname(Clientname);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findBydelegate")
	public ResponseEntity<List<pill>> findBydelegate(@RequestParam String delegate) throws HandlerException {

		List<pill> pi = pillServ.findByDelegate(delegate);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByEmployee")
	public ResponseEntity<List<pill>> findByEmployee(@RequestParam String employee) throws HandlerException {

		List<pill> pi = pillServ.findByEmployee(employee);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByID")
	public ResponseEntity<pill> findByID(@RequestParam("id") String id) throws HandlerException {

		pill pi = pillServ.findById(Long.decode(id));

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByclose")
	public ResponseEntity<List<pill>> findByprint(@RequestParam("print") Boolean print,
			@RequestParam("date") String date) throws HandlerException, ParseException {

		List<pill> pi = pillServ.findByPrint(print, date);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByTraficline")
	public ResponseEntity<List<pill>> findByTraficline(@RequestParam String traficline) throws HandlerException {

		List<pill> pi = pillServ.findByTraficline(traficline);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("findByTraficlineAndDate")
	public ResponseEntity<List<pill>> findByTraficlineAndDate(@RequestParam("traficline") String traficline,
			@RequestParam("date") String date) throws HandlerException, ParseException {

		List<pill> pi = pillServ.findByTraficlineAndDate(traficline, date);

		return new ResponseEntity<>(pi, OK);
	}

	@PostMapping("updatePillInfo")
	public ResponseEntity<pill> updatePillInfo(@RequestParam("id") String id, @RequestParam("date") String date,
			@RequestParam("clientname") String clientname) throws HandlerException, ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

		pill findPill = pillServ.findById(Long.decode(id));
		findPill.setEntrydate(SDF.parse(date));
		findPill.setClientname(clientname);

		pill pi = pillServ.save(findPill);

		return new ResponseEntity<>(pi, OK);

	}

}
