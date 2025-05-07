package com.ERPMatrix.Application.StaticMethod;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Service.finance.pill.pillDetailsServ;
import com.ERPMatrix.Application.Service.product.productServ;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service_implement.Client.ClientImpl;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillimpl;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service

public class AccountatTools {

	@Autowired
	private productbatchServ batchServ;
	@Autowired
	private ClientImpl cli;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private pillDetailsServ pillDetailsServ;
	@Autowired
	private pillimpl pillimpl;
	@Autowired
	private productServ proServ;

	public double addTaxCost(double price, int tax) {

		double taxCost = price - price / 100 * tax;
		return taxCost;
	}

	public double extraDiscount(double price, double discount) {

		double extraDiscount = price - price / 100 * discount;
		return extraDiscount;

	}

	public double getPriceAfterDiscount(double price, double discount) {
		double endPrice = price - price / 100 * discount;
		return endPrice;
	}

	public double getPriceAfterDiscountForTotalQouta(double price, double discount, int qouta) {
		double endPrice = price - price / 100 * discount;
		endPrice = endPrice * qouta;
		return endPrice;
	}

	public double getPriceBedforDiscount(double price, int qouta) {
		double endPrice = price * qouta;
		return endPrice;
	}

	public void minFromBatch(productbatch productbatch) throws HandlerException {
		Optional<productbatch> batch = batchServ.findById(productbatch.getId());

		batch.get().setProductqouta(batch.get().getProductqouta() - productbatch.getProductqouta());

		batchServ.update(batch.get());

	}

	public void minFromClient(client client) throws HandlerException {

		client cl = cli.findByName(client.getName());

		cl.setDebit(cl.getDebit() + client.getDebit());

		cli.insert(cl);

	}

	public void minFromPill(pill pill) throws HandlerException, ParseException {
		pill pi = pillimpl.findById(pill.getId());
		pi.setAmountafterdiscont(pi.getAmountafterdiscont() - pill.getAmountafterdiscont());
		pi.setAmountbefordiscount(pi.getAmountbefordiscount() - pill.getAmountbefordiscount());
		pi.setLastedit(new Date());
		pi.setProductnum(pi.getProductnum() - 1);
		pi.setEditemployee(pill.getEmployee());
		pillimpl.save(pi);

	}

	public void minFromPillDetails(pilldetails pilldetails) {

	}

	public void minFromProduct(product product, int qouta) throws HandlerException {
		product.setQouta(product.getQouta() - qouta);

		proServ.update(product);

	}

	public double minOldAndGetNew(double oldValue, double newValue, double staticValue) {

		double removeoldValue = (staticValue - oldValue);
		double endPrice = (removeoldValue + newValue);

		return endPrice;

	}

	public double minThis(double value1, double value2) {

		double endPrice;
		if (value1 > value2) {
			endPrice = value1 - value2;
		}

		else {
			endPrice = value2 - value1;
		}
		return endPrice;
	}

	public double plusThis(double value1, double value2) {
		double endPrice = value1 + value2;

		return endPrice;
	}

	public void plusToBatch(productbatch productbatch) {

	}

	public void plusToClient(client client) throws HandlerException {

		client cl = cli.findByName(client.getName());

		cl.setDebit(cl.getDebit() - client.getDebit());

		cli.insert(cl);

	}

	@Transactional
	public void plusToPill(pill pill) throws HandlerException, ParseException {
		pill piSave = new pill();
		piSave = pillimpl.findById(pill.getId());

		double afterDis;
		double beforDis;

		if (piSave.getAmountbefordiscount() == 0) {

			piSave.setAmountafterdiscont(pill.getAmountafterdiscont());
			piSave.setAmountbefordiscount(pill.getAmountbefordiscount());
			piSave.setLastedit(new Date());
			piSave.setProductnum(piSave.getProductnum() + 1);
			piSave.setEditemployee(pill.getEmployee());

		} else {
			afterDis = pill.getAmountafterdiscont() + piSave.getAmountafterdiscont();
			beforDis = pill.getAmountbefordiscount() + piSave.getAmountbefordiscount();

			piSave.setAmountafterdiscont(afterDis);
			piSave.setAmountbefordiscount(beforDis);
			piSave.setLastedit(new Date());
			piSave.setProductnum(piSave.getProductnum() + 1);
			piSave.setEditemployee(pill.getEmployee());

		}

		pillimpl.save(piSave);

	}

	public void plusToPillDetails(pilldetails pilldetails, int qouta) {

		List<pilldetails> pill = pillDetailsServ.insert(pilldetails);

	}

	public void plusToProduct(product product, int qouta) throws HandlerException {
		product.setQouta(product.getQouta() + qouta);

		proServ.update(product);

	}

	public double returnWeighedDiscount(int qty, int qouta, double dis, double discount, double price) {

		if (discount <= 0 || dis <= 0) {

			if (discount == 0) {
				return dis;
			} else {

				return discount;

			}

		} else {

			double firstQTYDis = getPriceAfterDiscountForTotalQouta(price, discount, qouta);
			double secQTYDis = getPriceAfterDiscountForTotalQouta(price, dis, qty);
			double totaQTYPrice = firstQTYDis + secQTYDis;
			int totaQty = qty + qouta;
			double productCost = totaQTYPrice / totaQty;
			double Discount = (1 - productCost / price) * 100;
			double Discount2 = Discount * 100;

			return Discount;
		}
	}

	public double returnWeighedDiscountAfterMin(int qty, double price) {

		double productCost = price / qty;
		double Discount = (1 - productCost / price) * 100;

		return Discount;

	}

}