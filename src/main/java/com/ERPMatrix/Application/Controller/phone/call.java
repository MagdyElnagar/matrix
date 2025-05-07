package com.ERPMatrix.Application.Controller.phone;

import static org.springframework.http.HttpStatus.OK;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.Phone.call_Model;
import com.ERPMatrix.Application.Model.User.User;

@RestController
@RequestMapping(path = { "/", "/call/" })
@CrossOrigin(origins = "*")
public class call {

	private com.ERPMatrix.Application.Service.phone.call call;

	@GetMapping("/call")
	public ResponseEntity<User> addNewUser() throws URISyntaxException {

		call_Model call_Model = new call_Model();
		call.call_this(call_Model);

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("saveDepit")
	public ResponseEntity<call_Model> saveDepit(@RequestBody call_Model call_Model) {

		return new ResponseEntity<>(null, OK);
	}

}
