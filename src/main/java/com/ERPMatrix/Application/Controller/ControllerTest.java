package com.ERPMatrix.Application.Controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.User.User;

@RestController
@RequestMapping(path = { "/", "/test/" })
@CrossOrigin(origins = "*")
public class ControllerTest {

	@GetMapping("/Array")
	public ResponseEntity<User> addNewUser() {

		return new ResponseEntity<>(null, OK);
	}

}
