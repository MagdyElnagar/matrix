package com.ERPMatrix.Application.Service_implement.finance.pill;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.employee.product_print_sort;
import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Repository.finance.pill.pilldetailsRepository;
import com.ERPMatrix.Application.Service.employee.product_sort_service;
import com.ERPMatrix.Application.Service.finance.pill.pillDetailsServ;
import com.ERPMatrix.Application.Service.product.productServ;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service_implement.Client.ClientImpl;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
public class pillDetailsImpl implements pillDetailsServ {
	@Autowired
	private productbatchServ batchServ;
	@Autowired
	private ClientImpl cli;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private pilldetailsRepository pilldetailsRepo;
	@Autowired
	private pillimpl pillimpl;
	@Autowired
	private product_sort_service product_sort_service;
	@Autowired
	private productServ proServ;
	@Autowired

	private AccountatTools tools;

	@Override
	public List<pilldetails> delete(pilldetails pilldetails)
			throws NumberFormatException, HandlerException, ParseException {
		Optional<pilldetails> rowCheak2 = pilldetailsRepo.findById(pilldetails.getId());
		pilldetails rowCheak = rowCheak2.get();
		pill pi = pillimpl.findById(Long.decode(pilldetails.getPillid()));

		client client = cli.findByName(pi.getClientname());
		product pro = proServ.findByName(pilldetails.getProductname());
		productbatch batch = new productbatch();

		/// i will remove this and replace it with find top 1 by name and price and qty
		/// :D
		/// thanks
		LOGGER.info(pilldetails.getPatchpillid());
		LOGGER.info(pilldetails.getProductname());

		if (pilldetails.getPatch() == null) {

			batch = batchServ.findByPillidAndProductname(pilldetails.getPatchpillid(), pilldetails.getProductname());

		} else {
			batch = batchServ.findByProductnameAndProductpriceAndBatch(pilldetails.getProductname(),
					pilldetails.getPrice(), pilldetails.getPatch());

		}

		int fakeQTY = pro.getQouta() + pilldetails.getQouta();

		if (batch == null) {

			throw new HandlerException("يوجد مشكلة فى التغشيلة", "pillDetailsImpl delete");

		} else {

			batch.setProductqouta(batch.getProductqouta() + pilldetails.getQouta());
			// update
			pro.setQouta(fakeQTY);

		}

		double ClientAmountCr = tools.minThis(rowCheak.getAmountafterdicount(), client.getCridet());
		double clientAmountDe = tools.plusThis(ClientAmountCr, client.getDebit());
		double PillAmountAfterDis = tools.minThis(rowCheak.getAmountafterdicount(), pi.getAmountafterdiscont());
		double PillAmount = tools.minThis(rowCheak.getAmount(), pi.getAmountbefordiscount());

		pi.setProductnum(pi.getProductnum() - 1);
		pi.setAmountafterdiscont(PillAmountAfterDis);
		pi.setAmountbefordiscount(PillAmount);

		client.setDebit(client.getDebit() - rowCheak.getAmountafterdicount());
		pillimpl.save(pi);

		pilldetailsRepo.deleteById(rowCheak.getId());
		return pilldetailsRepo.findByPillid(pilldetails.getPillid());
	}

	@Override
	public List<pilldetails> findAll() {
		// TODO Auto-generated method stub
		return pilldetailsRepo.findAll();
	}

	@Override
	public pilldetails findById(Long id) throws HandlerException {
		// TODO Auto-generated method stub
		Optional<pilldetails> row = pilldetailsRepo.findById(id);
		if (row.get() == null) {
			throw new HandlerException("هذا الصنف غير موجود", "pillDetailsImpl findById");
		} else {
			return row.get();

		}
	}

	@Override
	public List<pilldetails> findByPillid(String pillid) throws NumberFormatException, HandlerException {
		pill pill = pillimpl.findById(Long.decode(pillid));

		product_print_sort sorting = product_sort_service.findByEmployee(pill.getEditemployee());
		if (sorting.getSortby().equals("company")) {
			LOGGER.info("Sort By company");

			return pilldetailsRepo.findByPillidOrderByCompanyAsc(pillid);

		} else {
			return pilldetailsRepo.findByPillidOrderByProductnameAsc(pillid);

		}
	}

	@Override
	public List<pilldetails> findByProduct(String product) {
		// TODO Auto-generated method stub
		return pilldetailsRepo.findByProductname(product);
	}

	@Override
	public List<pilldetails> findByProductname(String productname) throws HandlerException {

		List<pilldetails> Pills = pilldetailsRepo.findByProductname(productname);
		
			return Pills;

	}

	@Override
	public pilldetails findByProductnameAndPillid(pilldetails pilldetails)throws HandlerException {
		pilldetails row = pilldetailsRepo.findByProductnameAndPillid(pilldetails.getProductname(),
				pilldetails.getPillid());
		if (row == null) {
			throw new HandlerException("هذا الصنف غير موجود", "pillDetailsImpl findByProductname");
		} else {
			return row;

		}
	}

	@Override
	public pilldetails findByProductnameAndPillidAndPatch(String product, String pillid, String patch) {
		// TODO Auto-generated method stub
		return pilldetailsRepo.findByProductnameAndPillidAndPatch(product, pillid, patch);
	}

	@Override
	public List<pilldetails> insert(pilldetails pilldetails) {

		pilldetailsRepo.save(pilldetails);
		return pilldetailsRepo.findByPillid(pilldetails.getPillid());

	}

	@Override
	@Transactional
	public List<pilldetails> save(pilldetails pilldetails) throws HandlerException, ParseException {
		pilldetails rowCheak = pilldetailsRepo.findByProductnameAndPillid(pilldetails.getProductname(),
				pilldetails.getPillid());
		LOGGER.info("getPillid " + pilldetails.getPillid());

		if (rowCheak == null) {
		} else {
			int qty = pilldetails.getQouta() + rowCheak.getQouta();

			pilldetails.setQouta(qty);

			this.delete(rowCheak);
		}
		LOGGER.info("Done");

		pill pill = pillimpl.findById(Long.decode(pilldetails.getPillid()));
		client client = cli.findByName(pill.getClientname());
		product pro = proServ.findByName(pilldetails.getProductname());
		List<productbatch> batch = batchServ.findByProductnameAndProductpriceAndProductqoutaGreaterThanOrderByExpire(
				pilldetails.getProductname(), pilldetails.getPrice(), 0);

		int proQouta = pro.getQouta();
		int fakeQouta = pilldetails.getQouta();

		if (pro.getQouta() < pilldetails.getQouta()) {
			throw new HandlerException("كمية الصنف لا تكفى", "pillDetailsImpl save");
		}

		if (fakeQouta > batch.get(0).getProductqouta()) {

			pro.setQouta((int) tools.minThis(fakeQouta, pro.getQouta()));
			for (int x = 0; x < batch.size(); x++) {
				pilldetails piDet = new pilldetails();
				client Fakeclient = new client();
				pill pillThrow = new pill();
				pillThrow = pill;
				Fakeclient.setName(client.getName());
				if (fakeQouta == 0) {
					break;
				}

				if (fakeQouta <= batch.get(x).getProductqouta()) {
					LOGGER.info("batch.get(x).getPillid() " + batch.get(x).getPillid());
					piDet.setCompany(pro.getCompany());
					piDet.setCliname(client.getName());
					piDet.setDate(new Date());
					piDet.setExpire(batch.get(x).getExpire());
					piDet.setPatch(batch.get(x).getBatch());
					piDet.setPatchpillid(batch.get(x).getPillid());

					piDet.setAmount(fakeQouta * batch.get(x).getProductprice());
					piDet.setAmountafterdicount(tools.getPriceAfterDiscountForTotalQouta(batch.get(x).getProductprice(),
							pilldetails.getDiscount(), fakeQouta));
					piDet.setDiscount(pilldetails.getDiscount());
					piDet.setPillid(pilldetails.getPillid());
					piDet.setPrice(batch.get(x).getProductprice());
					piDet.setProductname(batch.get(x).getProductname());
					piDet.setQouta(fakeQouta);

					pillThrow.setAmountafterdiscont(
							tools.getPriceAfterDiscountForTotalQouta(batch.get(x).getProductprice(),
									pilldetails.getDiscount(), fakeQouta) + pillThrow.getAmountafterdiscont());
					pillThrow.setAmountbefordiscount(
							tools.getPriceBedforDiscount(batch.get(x).getProductprice(), fakeQouta)
									+ pillThrow.getAmountbefordiscount());
					pillThrow.setProductnum(pillThrow.getProductnum() + 1);
					Fakeclient.setCridet(piDet.getAmountafterdicount());
					Fakeclient.setDebit(piDet.getAmountafterdicount());
					// tools.plusToPill(pillThrow);
					int patchQouta = batch.get(x).getProductqouta() - fakeQouta;
					int prodQouta = (int) tools.minThis(pro.getQouta(), fakeQouta);
					batch.get(x).setProductqouta(patchQouta);

					tools.plusToPillDetails(piDet, fakeQouta);
					tools.minFromClient(Fakeclient);

					fakeQouta = 0;
					break;
				} else {
					LOGGER.info("batch.get(x).getPillid() " + batch.get(x).getPillid());
					piDet.setCompany(pro.getCompany());
					piDet.setCliname(client.getName());
					piDet.setDate(new Date());
					piDet.setPatchpillid(batch.get(x).getPillid());
					piDet.setExpire(batch.get(x).getExpire());
					piDet.setPatch(batch.get(x).getBatch());
					piDet.setAmount(batch.get(x).getProductqouta() * batch.get(x).getProductprice());
					piDet.setAmountafterdicount(tools.getPriceAfterDiscountForTotalQouta(batch.get(x).getProductprice(),
							pilldetails.getDiscount(), batch.get(x).getProductqouta()));

					piDet.setDiscount(pilldetails.getDiscount());
					piDet.setPillid(pilldetails.getPillid());
					piDet.setPrice(batch.get(x).getProductprice());
					piDet.setProductname(batch.get(x).getProductname());
					piDet.setQouta(batch.get(x).getProductqouta());
					pillThrow.setProductnum(pillThrow.getProductnum() + 1);

					pillThrow.setAmountafterdiscont(tools.getPriceAfterDiscountForTotalQouta(
							batch.get(x).getProductprice(), pilldetails.getDiscount(), batch.get(x).getProductqouta())
							+ pillThrow.getAmountafterdiscont());

					pillThrow.setAmountbefordiscount(
							tools.getPriceBedforDiscount(batch.get(x).getProductprice(), batch.get(x).getProductqouta())
									+ pillThrow.getAmountbefordiscount());

					Fakeclient.setCridet(piDet.getAmountafterdicount());
					Fakeclient.setDebit(piDet.getAmountafterdicount());

					tools.plusToPillDetails(piDet, batch.get(x).getProductqouta());
					// tools.plusToPill(pillThrow);
					tools.minFromClient(Fakeclient);
					fakeQouta = (int) tools.minThis(fakeQouta, batch.get(x).getProductqouta());
					batch.get(x).setProductqouta(0);
					pilldetailsRepo.save(piDet);

				}

			}

		} else {
			LOGGER.info("one Row");
			pilldetails piDet = new pilldetails();
			client Fakeclient = new client();
			piDet.setCliname(client.getName());
			piDet.setDate(new Date());
			Fakeclient.setName(client.getName());
			int x = 0;
			LOGGER.info("batch.get(x).getPillid() " + batch.get(x).getPillid());
			piDet.setCompany(pro.getCompany());
			piDet.setExpire(batch.get(x).getExpire());
			piDet.setPatch(batch.get(x).getBatch());
			piDet.setAmount(pilldetails.getQouta() * batch.get(x).getProductprice());

			piDet.setAmountafterdicount(tools.getPriceAfterDiscountForTotalQouta(batch.get(x).getProductprice(),
					pilldetails.getDiscount(), pilldetails.getQouta()));
			piDet.setPatchpillid(batch.get(x).getPillid());

			piDet.setDiscount(pilldetails.getDiscount());
			piDet.setPillid(pilldetails.getPillid());
			piDet.setPrice(batch.get(x).getProductprice());
			piDet.setProductname(batch.get(x).getProductname());
			piDet.setQouta(pilldetails.getQouta() + piDet.getQouta());
			Fakeclient.setCridet(piDet.getAmountafterdicount());
			Fakeclient.setDebit(piDet.getAmountafterdicount());
			tools.minFromClient(Fakeclient);
			tools.plusToPillDetails(piDet, pilldetails.getQouta());
			pill.setAmountafterdiscont(pill.getAmountafterdiscont() + tools.getPriceAfterDiscountForTotalQouta(
					batch.get(x).getProductprice(), pilldetails.getDiscount(), pilldetails.getQouta()));
			pill.setAmountbefordiscount(pill.getAmountbefordiscount()
					+ tools.getPriceBedforDiscount(batch.get(x).getProductprice(), pilldetails.getQouta()));
			pill.setProductnum(pill.getProductnum() + 1);

			int patchQouta = (int) tools.minThis(batch.get(x).getProductqouta(), fakeQouta);
			int prodQouta = (int) tools.minThis(pro.getQouta(), fakeQouta);

			LOGGER.info("QTY " + String.valueOf(patchQouta));
			LOGGER.info("QTY " + String.valueOf(prodQouta));

			batch.get(x).setProductqouta(patchQouta);
			pro.setQouta(prodQouta);
			pilldetailsRepo.save(piDet);

		}
		product_print_sort sorting = product_sort_service.findByEmployee(pill.getEmployee());
		if (sorting.getSortby().equals("company")) {

			return pilldetailsRepo.findByPillidOrderByCompanyAsc(pilldetails.getPillid());

		} else {
			return pilldetailsRepo.findByPillidOrderByProductnameAsc(pilldetails.getPillid());

		}

	}

	@Override
	public List<pilldetails> update(pilldetails pilldetails) {

		pilldetails rowCheak = pilldetailsRepo.findByProductnameAndPillid(pilldetails.getProductname(),
				pilldetails.getPillid());

		/*
		 * find row delete row add new data save :D
		 */
		pilldetailsRepo.save(pilldetails);
		return pilldetailsRepo.findByPillid(pilldetails.getPillid());

	}

}
