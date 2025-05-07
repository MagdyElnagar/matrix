package com.ERPMatrix.Application.Controller.admin;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.admin.Data_Base_BackUp;
import com.ERPMatrix.Application.Repository.Admin.database_backup;

@RestController
@RequestMapping(path = { "/", "/database" })
@CrossOrigin(origins = "*")
public class DataBase_Controller {

	@Autowired
	private database_backup database_backup;

	@GetMapping("/findAll")
	public ResponseEntity<List<Data_Base_BackUp>> findAll() {

		List<Data_Base_BackUp> PK = database_backup.findAll();

		return new ResponseEntity<>(PK, OK);

	}

}
