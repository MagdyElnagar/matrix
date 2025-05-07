package com.ERPMatrix.Application.tools;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Service.product.productServ;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespillServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespilldetailsServ;
import com.ERPMatrix.Application.Service.purchases.supliser.supliserServ;

public class supliserTools {

	private productbatchServ patchServ;
	private salespilldetailsServ pillDetailsServ;
	private salespillServ pillServ;
	private productServ productServ;
	private supliserServ supliserServ;

	@Autowired
	public supliserTools(salespillServ pillServ, salespilldetailsServ pillDetailsServ, productbatchServ patchServ,
			productServ productServ, supliserServ supliserServ) {
		super();
		this.pillServ = pillServ;
		this.pillDetailsServ = pillDetailsServ;
		this.patchServ = patchServ;
		this.productServ = productServ;
		this.supliserServ = supliserServ;
	}

	public void minFromPatch() {
	}

	public void minFromPill(supliserpill supliserpill) {

		Optional<supliserpill> oldPill = pillServ.findById(supliserpill.getId());
		supliserpill row = new supliserpill();
		row = oldPill.get();
		row.setTotalafterdis(supliserpill.getTotalafterdis() - oldPill.get().getTotalafterdis());
		row.setTotalbefordis(supliserpill.getTotalafterdis() - oldPill.get().getTotalbefordis());
		row.setLastedit(new Date());
		row.setEditemployeename(supliserpill.getEditemployeename());
		pillServ.save(row);
	}

	public void minFromPillDetail(supliserpilldetails supliserpilldetails) {
		supliserpilldetails oldDetails = pillDetailsServ.findByProductAndPillId(supliserpilldetails.getProductname(),
				supliserpilldetails.getPillid());
		supliserpilldetails row = new supliserpilldetails();

		row = oldDetails;
	}

	public void minFromProduct() {
	}

	public void minFromSupliser() {
	}

	public void plusToPatch(productbatch productbatch) {
	}

	public void plusToPill(supliserpill supliserpill) {
		Optional<supliserpill> oldPill = pillServ.findById(supliserpill.getId());
		supliserpill row = new supliserpill();
		row = oldPill.get();
		row.setTotalafterdis(supliserpill.getTotalafterdis() + oldPill.get().getTotalafterdis());
		row.setTotalbefordis(supliserpill.getTotalafterdis() + oldPill.get().getTotalbefordis());
		row.setLastedit(new Date());
		row.setEditemployeename(supliserpill.getEditemployeename());
		pillServ.save(row);
	}

	public void plusToPillDetail(supliserpilldetails supliserpilldetails) {

		supliserpilldetails oldDetails = pillDetailsServ.findByProductAndPillId(supliserpilldetails.getProductname(),
				supliserpilldetails.getPillid());
		supliserpilldetails row = new supliserpilldetails();
		row = oldDetails;

	}

	public void plusToProduct(product product) {
	}

	public void plusToSupliser(supliser supliser) {
	}

}
