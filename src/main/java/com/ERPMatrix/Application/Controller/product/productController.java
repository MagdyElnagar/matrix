package com.ERPMatrix.Application.Controller.product;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Constant.HttpResponse;
import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Service.product.productServ;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.finance.pill.pillDetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/product/" })
@CrossOrigin(origins = "*")

public class productController extends ExceptionHandling {

	private productServ productServ;
	private productbatchServ proPatchServ;
	private pillDetailsServ pillDetailsServ;

	@Autowired
	public productController(productServ productServ, productbatchServ proPatchServ ,pillDetailsServ pillDetailsServ) {
		this.productServ = productServ;
		this.proPatchServ = proPatchServ;
		this.pillDetailsServ= pillDetailsServ;
	}

	@GetMapping("findAll")
	public ResponseEntity<List<product>> findAll() {

		List<product> findAll = productServ.findAll();

		return new ResponseEntity<>(findAll, OK);
	}



	@PostMapping("findByProductName")
	public ResponseEntity<product> findByProductName(@RequestParam String productname) throws HandlerException {

		product editProduct = productServ.findByName(productname);

		return new ResponseEntity<>(editProduct, OK);
	}

	@PostMapping("findByProductNameAndStore")
	public ResponseEntity<product> findByProductNameAndStore(@RequestParam("name") String productname,
			@RequestParam("store") String store, @RequestParam("price") double price, @RequestParam("qty") int qty)
			throws HandlerException {

		List<productbatch> batch = proPatchServ
				.findByProductnameAndStoreAndProductpriceAndProductqoutaGreaterThanOrderByExpire(productname, store,
						price, qty);

		product editProduct = productServ.findByName(productname);
		editProduct.setQouta(0);

		for (int x = 0; x < batch.size(); x++) {

			editProduct.setQouta(editProduct.getQouta() + batch.get(x).getProductqouta());

		}

		return new ResponseEntity<>(editProduct, OK);
	}

	@PostMapping("findByProductNameAndStoreAndStatus")
	public ResponseEntity<product> findByProductNameAndStoreAndStatus(@RequestParam("name") String name,
			@RequestParam("store") String store) throws HandlerException {

		product product = proPatchServ.findByNameAndStatusAndStore(name, store);
		return new ResponseEntity<>(product, OK);
	}

	@PostMapping("findByStatus")
	public ResponseEntity<List<product>> findByStatus(@RequestBody product product) throws HandlerException {

		List<product> editProduct = productServ.findByStatus(product.isStatus());
		return new ResponseEntity<>(editProduct, OK);
	}

	@GetMapping("/productGreatherthanZero")
	public List<product> findGreatherthanZeroQouta() {

		return productServ.findByGreatherthan(0);

	}
	
	@PostMapping("/SoldProductByName")
	public ResponseEntity<List<pilldetails>> SoldProductByName(@RequestParam String productname) throws HandlerException {

		List<pilldetails> Pills =	pillDetailsServ.findByProductname(productname);
		

		return new ResponseEntity<>(Pills, OK);
	}


	@PostMapping("findProductForFinancePill")
	public ResponseEntity<product> findProductForFinancePill(@RequestParam("name") String productname,
			@RequestParam("store") String store, @RequestParam("price") double price, @RequestParam("qty") int qty)
			throws HandlerException {

		product editProduct = productServ.findByName(productname);

		return new ResponseEntity<>(editProduct, OK);
	}
	
	

	@GetMapping("findWorkedProduct")
	public ResponseEntity<List<product>> findWorkedProduct() {

		List<product> findAll = productServ.findByStatus(true);

		return new ResponseEntity<>(findAll, OK);
	}

	@GetMapping("/findZeroQouta")
	public List<product> findZeroQouta() {

		return productServ.findByZeroQouta(0);

	}

	@RequestMapping("/findbychar/{product}")
	public List<product> getSearchMedicalsList(@PathVariable String product) {

		return productServ.findByNameIsContaining(product);

	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}

	@PostMapping("save")
	public ResponseEntity<product> saveProduct(@RequestBody product product) throws HandlerException {

		product saveProd = productServ.Save(product);

		return new ResponseEntity<>(saveProd, OK);
	}

	@PostMapping("update")
	public ResponseEntity<product> updateProduct(@RequestBody product product) throws HandlerException {

		product editProduct = productServ.update(product);
		return new ResponseEntity<>(editProduct, OK);
	}

}
