package com.ERPMatrix.Application.Controller.product;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.compare.compareModel1;
import com.ERPMatrix.Application.Model.compare.compareModel2;
import com.ERPMatrix.Application.Repository.File.compare1;
import com.ERPMatrix.Application.Repository.File.compare2;
import com.ERPMatrix.Application.exception.ExceptionHandling;

@RestController
@Configuration
@RequestMapping(path = { "/", "/compare/" })
@CrossOrigin(origins = "*")

public class compareController  extends ExceptionHandling{
	private compare1 compare1;
	private compare2 compare2;
	
	@Autowired
	public compareController(compare1 compare1, compare2 compare2) {
		this.compare1 = compare1;
		this.compare2 = compare2;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<compareModel1>> findAll() {
		List<compareModel1> firstList = compare1.findAll();
		System.out.println("First_List_Size : "+firstList.size());

		for(int i =1 ; i<=firstList.size();i++) {
			List<compareModel2> compare = compare2.findByNameLike(firstList.get(i).getName());
			System.out.println("Row : " + i);
			System.out.println(firstList.get(i).getName());
		if(compare.size() >1) {
			for(int x =0 ;x<=compare.size();x++) {
				
				System.out.println(compare.get(x).getName());
			}
		}
		}
		
		
		
		
		return new ResponseEntity<>(null, OK);
	}
	
	
	
	
	

}
