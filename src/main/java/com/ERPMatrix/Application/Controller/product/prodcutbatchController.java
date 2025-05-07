package com.ERPMatrix.Application.Controller.product;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespilldetailsServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@RestController
@Configuration
@RequestMapping(path = { "/", "/productBatch/" })
@CrossOrigin(origins = "*")

public class prodcutbatchController extends ExceptionHandling {
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	private salespilldetailsServ pillDetailsServ;
	private productbatchServ prodcutbatchServ;

	@Autowired
	public prodcutbatchController(productbatchServ prodcutbatchServ, salespilldetailsServ pillDetailsServ) {
		this.prodcutbatchServ = prodcutbatchServ;
		this.pillDetailsServ = pillDetailsServ;
	}

	@PostMapping("addPillPatch")
	public ResponseEntity<productbatch> addPillPatch(@RequestBody productbatch productbatch) throws HandlerException {

		supliserpilldetails details = pillDetailsServ.findByProductAndPillId(productbatch.getProductname(),
				productbatch.getPillid());
		productbatch probatch = prodcutbatchServ.add(productbatch, details);

		return new ResponseEntity<>(probatch, OK);
	}

	@PostMapping("addProductBatch")
	public ResponseEntity<productbatch> addProductBatch(@RequestBody productbatch productbatch)
			throws HandlerException {

		productbatch saveProd = prodcutbatchServ.save(productbatch);

		return new ResponseEntity<>(saveProd, OK);
	}

	@PostMapping("deletePatchFromPill")
	public ResponseEntity<List<productbatch>> deletePatchFromPill(@RequestBody productbatch productbatch)
			throws HandlerException {

		List<productbatch> patches = prodcutbatchServ.deleteFromPill(productbatch.getId());

		return new ResponseEntity<>(patches, OK);
	}

	@PostMapping("deleteProductBatch")
	public ResponseEntity<productbatch> deleteProductBatch(@RequestBody productbatch productbatch)
			throws HandlerException {
		System.out.println("Delete product batch");
		prodcutbatchServ.deleteBatch(productbatch.getId());

		return new ResponseEntity<>(OK);
	}

	@PostMapping("findByProductname")
	public ResponseEntity<List<productbatch>> findByProductname(@RequestParam String productname)
			throws HandlerException {
		List<productbatch> probatch = prodcutbatchServ.findByProductname(productname);

		return new ResponseEntity<>(probatch, OK);
	}

	@PostMapping("findByProductnameAndPillid")
	public ResponseEntity<List<productbatch>> findByProductnameAndPillid(@RequestBody productbatch productbatch)
			throws HandlerException {

		List<productbatch> probatch = prodcutbatchServ.findByProductnameAndPillid(productbatch.getProductname(),
				productbatch.getPillid());

		return new ResponseEntity<>(probatch, OK);
	}

	@PostMapping("findByProductnameOrderByDate")
	public ResponseEntity<List<productbatch>> findByProductnameOrderByDate(@RequestParam String productname)
			throws HandlerException {
		List<productbatch> probatch = prodcutbatchServ.findByProductnameOrderByExpire(productname);

		return new ResponseEntity<>(probatch, OK);
	}

	@PostMapping("findProductBatch")
	public ResponseEntity<List<productbatch>> findProductBatch(@RequestParam String productid) throws HandlerException {

		List<productbatch> saveProd = prodcutbatchServ.findByProductid(productid);

		return new ResponseEntity<>(saveProd, OK);
	}

	@PostMapping("getFullDetailsForProduct")
	public ResponseEntity<List<productbatch>> getFullDetailsForProduct(@RequestParam("productname") String name)
			throws HandlerException {

		List<productbatch> saveProd = prodcutbatchServ.getFullDetailsForProduct(name);

		return new ResponseEntity<>(saveProd, OK);
	}

	@PostMapping("updateProductAutomatic")
	public ResponseEntity<productbatch> updateProductAutomatic(
			@RequestBody com.ERPMatrix.Application.Model.product.product product) throws HandlerException {

		prodcutbatchServ.updatePatchesDiscount(product.getName(), product.getPrice());

		return new ResponseEntity<>(null, OK);
	}

	@PostMapping("updateProductBatch")
	public ResponseEntity<productbatch> updateProductBatch(@RequestBody productbatch productbatch)
			throws HandlerException {
		System.out.println("Update product batch");
		productbatch update = prodcutbatchServ.update(productbatch);

		return new ResponseEntity<>(update, OK);
	}

}
