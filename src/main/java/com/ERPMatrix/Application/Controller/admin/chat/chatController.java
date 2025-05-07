package com.ERPMatrix.Application.Controller.admin.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.chat.chatModel;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@RequestMapping(path = { "/", "/chat/" })
@CrossOrigin(origins = "*")
public class chatController extends ExceptionHandling {

	@PostMapping("updateStatus")

	public ResponseEntity<chatModel> updateStatus(@RequestParam("username") String username) {
		return null;
	}

}
