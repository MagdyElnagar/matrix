package com.ERPMatrix.Application.Service_implement.purchases.pill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpill;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Repository.product.productRepository;
import com.ERPMatrix.Application.Repository.purchases.pill.supliserpillRepository;
import com.ERPMatrix.Application.Repository.purchases.pill.supliserpilldetailsRepository;
import com.ERPMatrix.Application.Repository.purchases.supliser.supliserRepository;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespillServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespilldetailsServ;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;

@Service
@Transactional
public class supliserpilldetailsImpl implements salespilldetailsServ {
	private AccountatTools accountatTools;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	private productbatchServ patchServ;
	private supliserpilldetailsRepository pillDetailsRepo;
	private supliserpillRepository pillRepo;
	private productRepository prodRepo;
	private salespillServ salespillServ;
	private supliserRepository supRepo;

	@Autowired
	public supliserpilldetailsImpl(AccountatTools accountatTools, supliserpillRepository pillRepo,
			productRepository prodRepo, salespillServ salespillServ, supliserpilldetailsRepository pillDetailsRepo,
			supliserRepository supRepo, productbatchServ patchServ) {
		super();
		this.accountatTools = accountatTools;
		this.patchServ = patchServ;
		this.pillRepo = pillRepo;
		this.prodRepo = prodRepo;
		this.salespillServ = salespillServ;
		this.pillDetailsRepo = pillDetailsRepo;
		this.supRepo = supRepo;
	}

	@Override
	public List<supliserpilldetails> changePillSupliser(supliserpill supliserpill) {

		return null;
	}

	public List<supliserpilldetails> ConvertOrder(supliserpilldetails salespilldetails) {

		supliserpilldetails check = pillDetailsRepo.findByPillidAndProductname(salespilldetails.getPillid(),
				salespilldetails.getProductname());
		product prod = prodRepo.findByName(salespilldetails.getProductname());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(salespilldetails.getPillid()));
		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		double totalQoutaPrice = salespilldetails.getProductprice() * salespilldetails.getProductqouta();
		double priceTotalQoutaAfterDisc = totalQoutaPrice
				- totalQoutaPrice / 100 * salespilldetails.getProductdiscount();

		salespilldetails.setTotalafterdiscount(priceTotalQoutaAfterDisc);
		salespilldetails.setTotalbeforiscount(totalQoutaPrice);

		sup.setDebit(sup.getDebit() + salespilldetails.getTotalafterdiscount());
		// prod.setQouta(prod.getQouta() + salespilldetails.getProductqouta());

		// prodRepo.save(prod);
		supRepo.save(sup);
		pillDetailsRepo.save(salespilldetails);
		return pillDetailsRepo.findByPillid(salespilldetails.getPillid());

	}

	@Override
	public List<supliserpilldetails> delete(supliserpilldetails salespilldetails) throws Exception {

		Optional<supliserpilldetails> rowData = pillDetailsRepo.findById(salespilldetails.getId());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(rowData.get().getPillid()));
		List<productbatch> patch = patchServ.findByProductnameAndPillid(rowData.get().getProductname(),
				rowData.get().getPillid());
		LOGGER.info("delete pill details product name : " + rowData.get().getProductname());
		updatePillDate(orderdata.get());

		if (patch == null) {

		} else {

			for (int z = 0; z < patch.size(); z++) {
				patchServ.deleteFromPill(patch.get(z).getId());

			}
		}

		supliserpill pill = orderdata.get();

		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		double SupDebit = sup.getDebit();
		double minThis = rowData.get().getTotalafterdiscount();
		double setThis = SupDebit - minThis;

		pill.setTotalafterdis(pill.getTotalafterdis() - rowData.get().getTotalafterdiscount());
		pill.setTotalbefordis(pill.getTotalbefordis() - rowData.get().getTotalbeforiscount());
		patchServ.updatePatchesDiscount(rowData.get().getProductname(), rowData.get().getProductprice());
		sup.setDebit(setThis);
		supRepo.save(sup);
		pillDetailsRepo.deleteById(rowData.get().getId());
		pillRepo.save(pill);
		updatePillDate(orderdata.get());
		return pillDetailsRepo.findByPillid(rowData.get().getPillid().toString());
	}

	@Override
	public void deleteAll(String pillid) {
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(pillid));

		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());

		sup.setDebit(sup.getDebit() - orderdata.get().getDebittosupliser());

		orderdata.get().setDebittosupliser(0);
		orderdata.get().setTotalbefordis(0);

		pillRepo.save(orderdata.get());
		supRepo.save(sup);
		pillDetailsRepo.deleteByPillid(pillid);

	}

	@Override
	public List<supliserpilldetails> deleteByPillid(supliserpilldetails supliserpilldetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserpilldetails> deleteRow(supliserpilldetails supliserpilldetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public supliserpilldetails findById(Long id) {

		Optional<supliserpilldetails> supPilLDetails = pillDetailsRepo.findById(id);
		supliserpilldetails PillDetails = supPilLDetails.get();
		return PillDetails;
	}

	@Override
	public List<supliserpilldetails> findByPillid(String pillid) {
		// TODO Auto-generated method stub
		return pillDetailsRepo.findByPillid(pillid);
	}

	@Override
	public List<supliserpilldetails> findByProduct(String productname) {
		// TODO Auto-generated method stub

		return pillDetailsRepo.findByProductname(productname);
	}

	@Override
	public supliserpilldetails findByProductAndPillId(String productname, String pillid) {

		return pillDetailsRepo.findByPillidAndProductname(pillid, productname);
	}

	@Override
	public supliserpilldetails findByProductAndPillId(supliserpilldetails supliserpilldetails) {
		supliserpilldetails row = pillDetailsRepo.findByPillidAndProductname(supliserpilldetails.getPillid(),
				supliserpilldetails.getProductname());

		return row;
	}

	@Override
	public Optional<supliserpilldetails> findBysupliserpilldetailsID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<supliserpilldetails> getLast5Record(String productname) {
		List<supliserpilldetails> p = pillDetailsRepo.findByProductname(productname);

		if (p.size() <= 4) {
			return p;

		} else {
			List<supliserpilldetails> passThis = new ArrayList<supliserpilldetails>();

			passThis.add(p.get(p.size() - 5));
			passThis.add(p.get(p.size() - 4));
			passThis.add(p.get(p.size() - 3));
			passThis.add(p.get(p.size() - 2));
			passThis.add(p.get(p.size() - 1));

			return passThis;
		}
	}

	@Override
	public supliserpilldetails getLastRecordForProduct(String productname) {

		List<supliserpilldetails> p = pillDetailsRepo.findByProductname(productname);
		if (p.size() > 0) {
			return p.get(p.size() - 1);
		} else {

			return p.get(0);
		}

	}

	@Override
	public List<supliserpilldetails> save(supliserpilldetails salespilldetails) {

		supliserpilldetails check = pillDetailsRepo.findByPillidAndProductname(salespilldetails.getPillid(),
				salespilldetails.getProductname());

		product prod = prodRepo.findByName(salespilldetails.getProductname());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(salespilldetails.getPillid()));
		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		supliserpill pilldata = orderdata.get();

		double discount = salespilldetails.getProductdiscount();
		double totalQoutaPrice = salespilldetails.getProductprice() * salespilldetails.getProductqouta();
		double priceTotalQoutaAfterDisc = totalQoutaPrice
				- totalQoutaPrice / 100 * salespilldetails.getProductdiscount();
		double pillOlderTotalAfter = orderdata.get().getTotalafterdis();
		double pillOlderTotalBefor = orderdata.get().getTotalbefordis();
		salespilldetails.setTotalafterdiscount(priceTotalQoutaAfterDisc);
		salespilldetails.setTotalbeforiscount(totalQoutaPrice);
		double newTotalAfterDiscount = priceTotalQoutaAfterDisc;
		double newTotalBeforDiscount = totalQoutaPrice;
		double oldTotalAfterDiscount = pilldata.getTotalafterdis();
		double oldTotalBeforDiscount = pilldata.getTotalbefordis();
		salespilldetails.setDate(new Date());
		salespilldetails.setSupname(sup.getName());
		if (check == null) {
			LOGGER.info("Cheak is null");
			sup.setDebit(sup.getDebit() + salespilldetails.getTotalafterdiscount());
			/*
			 * prod.setWeigheddiscount(
			 * accountatTools.returnWeighedDiscount(salespilldetails.getProductqouta(),
			 * prod.getQouta(), salespilldetails.getProductdiscount(),
			 * prod.getWeigheddiscount(), prod.getPrice()));
			 *
			 * prod.setQouta(prod.getQouta() + salespilldetails.getProductqouta());
			 */
			if (orderdata.get().getTotalafterdis() == 0) {
				// orderdata.get().setTotalafterdis(priceTotalQoutaAfterDisc);
				// orderdata.get().setTotalbefordis(totalQoutaPrice);
				pilldata.setTotalafterdis(priceTotalQoutaAfterDisc);
				pilldata.setTotalbefordis(totalQoutaPrice);
			} else {
				// orderdata.get().setTotalafterdis(pillOlderTotalAfter +
				// priceTotalQoutaAfterDisc);
				// orderdata.get().setTotalbefordis(pillOlderTotalBefor + totalQoutaPrice);
				pilldata.setTotalafterdis(pillOlderTotalAfter + priceTotalQoutaAfterDisc);
				pilldata.setTotalbefordis(pillOlderTotalBefor + totalQoutaPrice);
			}

			// prodRepo.save(prod);
			supRepo.save(sup);
			pillRepo.save(pilldata);
			pillDetailsRepo.save(salespilldetails);

		} else {
			LOGGER.info("Cheak not null");

			pilldata.setTotalafterdis(pilldata.getTotalafterdis() - check.getTotalafterdiscount());
			pilldata.setTotalbefordis(pilldata.getTotalbefordis() - check.getTotalbeforiscount());

			int totalqouta = salespilldetails.getProductqouta() + check.getProductqouta();
			discount = salespilldetails.getProductdiscount();
			totalQoutaPrice = salespilldetails.getProductprice() * totalqouta;
			priceTotalQoutaAfterDisc = totalQoutaPrice - totalQoutaPrice / 100 * salespilldetails.getProductdiscount();

			salespilldetails.setTotalafterdiscount(priceTotalQoutaAfterDisc);
			salespilldetails.setTotalbeforiscount(totalQoutaPrice);

			sup.setDebit(sup.getDebit() - check.getTotalafterdiscount());
			sup.setDebit(sup.getDebit() + priceTotalQoutaAfterDisc);

			// prod.setQouta(prod.getQouta() - check.getProductqouta());
			// prod.setQouta(prod.getQouta() + totalqouta);

			check.setProductdiscount(salespilldetails.getProductdiscount());
			check.setProductqouta(totalqouta);
			check.setTotalafterdiscount(priceTotalQoutaAfterDisc);
			check.setTotalbeforiscount(totalQoutaPrice);
			check.setProductBounce(salespilldetails.getProductBounce());

			pilldata.setTotalafterdis(pilldata.getTotalafterdis() + priceTotalQoutaAfterDisc);

			pilldata.setTotalbefordis(pilldata.getTotalbefordis() + totalQoutaPrice);

			// prodRepo.save(prod);
			supRepo.save(sup);

			pillDetailsRepo.save(check);
			pillRepo.save(pilldata);

		}

		return pillDetailsRepo.findByPillid(salespilldetails.getPillid());
	}

	@Override
	public List<supliserpilldetails> saveRow(supliserpilldetails supliserpilldetails) throws Exception {
		supliserpilldetails check = pillDetailsRepo.findByPillidAndProductname(supliserpilldetails.getPillid(),
				supliserpilldetails.getProductname());
		if (check == null) {
		} else {
			delete(check);

		}
		supliserpilldetails.setDate(new Date());
		product prod = prodRepo.findByName(supliserpilldetails.getProductname());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(supliserpilldetails.getPillid()));
		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		supliserpill pilldata = orderdata.get();
		double totaAfterDis = accountatTools.getPriceAfterDiscountForTotalQouta(prod.getPrice(),
				supliserpilldetails.getProductdiscount(), supliserpilldetails.getProductqouta());
		supliserpilldetails.setSupname(sup.getName());

		double totalAmount = supliserpilldetails.getProductprice() * supliserpilldetails.getProductqouta();
		supliserpilldetails.setTotalafterdiscount(totaAfterDis);
		supliserpilldetails.setTotalbeforiscount(totalAmount);
		double pillAfterDiscount = pilldata.getTotalafterdis() + totaAfterDis;
		double pillBeforDiscount = pilldata.getTotalbefordis() + totalAmount;
		double supDebit = sup.getDebit() + totaAfterDis;

		pilldata.setTotalafterdis(pillAfterDiscount);
		pilldata.setTotalbefordis(pillBeforDiscount);
		sup.setDebit(supDebit);
		int proQTY = prod.getQouta() + supliserpilldetails.getProductqouta();
		double WEDiscount = accountatTools.returnWeighedDiscount(prod.getQouta(), supliserpilldetails.getProductqouta(),
				prod.getWeigheddiscount(), supliserpilldetails.getProductdiscount(), prod.getPrice());

		prod.setWeigheddiscount(WEDiscount);

		prod.setQouta(proQTY);

		int totalQty = 0;
		productbatch newRow = new productbatch();
		newRow.setProductname(supliserpilldetails.getProductname());
		// newRow.setBatch(supliserpilldetails.getBatch());
		newRow.setEntrydate(new Date());
		// newRow.setExpire(productbatch.getExpire());
		newRow.setPillid(supliserpilldetails.getPillid());
		newRow.setProductdiscount(supliserpilldetails.getProductdiscount());
		newRow.setProductid(String.valueOf(prod.getId()));
		newRow.setProductprice(prod.getPrice());
		newRow.setProductqouta(supliserpilldetails.getProductqouta());
		newRow.setStore(pilldata.getStore());
		newRow.setSupliser(pilldata.getSuplisername());
		patchServ.save(newRow);
		supRepo.save(sup);
		// pillRepo.save(pilldata);
		pillDetailsRepo.save(supliserpilldetails);

		updatePillDate(pilldata);
		patchServ.updatePatchesDiscount(prod.getName(), prod.getPrice());

		return pillDetailsRepo.findByPillid(supliserpilldetails.getPillid());
	}

	public void updatePillDate(supliserpill orderdata) {
		List<supliserpilldetails> pillDetails = pillDetailsRepo.findByPillid(String.valueOf(orderdata.getId()));
		Optional<supliserpill> pill = pillRepo.findById(orderdata.getId());
		if (pillDetails == null) {
			pill.get().setTotalafterdis(0);
			pill.get().setTotalbefordis(0);
		} else {
			double befordis = 0;
			double afterdis = 0;

			for (int x = 0; x < pillDetails.size(); x++) {

				befordis = befordis + pillDetails.get(x).getTotalbeforiscount();
				afterdis = afterdis + pillDetails.get(x).getTotalafterdiscount();
			}
			pill.get().setTotalafterdis(afterdis);
			pill.get().setTotalbefordis(befordis);

		}

		pillRepo.save(pill.get());

	}

	@Override

	public List<supliserpilldetails> updateRow(supliserpilldetails supliserpilldetails) {

		LOGGER.info("pill id " + supliserpilldetails.getPillid());
		LOGGER.info("id " + supliserpilldetails.getId().toString());

		supliserpilldetails row = pillDetailsRepo.findByPillidAndId(supliserpilldetails.getPillid(),
				supliserpilldetails.getId());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(supliserpilldetails.getPillid()));
		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		product prod = prodRepo.findByName(row.getProductname());
		int oldQouta = row.getProductqouta();
		double oldDiscount = row.getProductdiscount();
		double oldTotalAfterDiscount = row.getTotalafterdiscount();
		double oldTotaBeforDiscount = row.getTotalbeforiscount();

		double newpricePerone = accountatTools.getPriceAfterDiscount(supliserpilldetails.getProductprice(),
				supliserpilldetails.getProductdiscount());
		double newTotaAfterDisc = accountatTools.getPriceAfterDiscountForTotalQouta(
				supliserpilldetails.getProductprice(), supliserpilldetails.getProductdiscount(),
				supliserpilldetails.getProductqouta());

		double newTotaBeforDisc = accountatTools.getPriceBedforDiscount(supliserpilldetails.getProductprice(),
				supliserpilldetails.getProductqouta());
		double oldOrderTAfterDis = orderdata.get().getTotalafterdis();
		double oldOrderTBefotDis = orderdata.get().getTotalbefordis();

		double minAfterDis = accountatTools.minOldAndGetNew(oldTotalAfterDiscount, newTotaAfterDisc, oldOrderTAfterDis);
		double minBeforDis = accountatTools.minOldAndGetNew(oldTotaBeforDiscount, newTotaBeforDisc, oldOrderTBefotDis);

		prod.setQouta(
				(int) accountatTools.minOldAndGetNew(oldQouta, supliserpilldetails.getProductqouta(), prod.getQouta()));

		sup.setDebit(accountatTools.minOldAndGetNew(row.getTotalafterdiscount(), newTotaAfterDisc, sup.getDebit()));

		orderdata.get().setTotalafterdis(minAfterDis);
		orderdata.get().setTotalbefordis(minBeforDis);
		pillRepo.save(orderdata.get());
		row.setProductBounce(supliserpilldetails.getProductBounce());
		row.setProductdiscount(supliserpilldetails.getProductdiscount());
		row.setProductqouta(supliserpilldetails.getProductqouta());
		row.setTotalafterdiscount(newTotaAfterDisc);
		row.setTotalbeforiscount(newTotaBeforDisc);
		return pillDetailsRepo.findByPillid(supliserpilldetails.getPillid());
		/*
		 *
		 * supliserpilldetails row =
		 * pillDetailsRepo.findByPillidAndId(supliserpilldetails.getPillid(),
		 * supliserpilldetails.getId());
		 *
		 * supliserpill orderdata =
		 * pillRepo.findOneById(Long.decode(supliserpilldetails.getPillid()));
		 *
		 * supliser sup = supRepo.findByName(orderdata.getSuplisername()); double
		 * oldSupDebit = sup.getDebit(); int oldQouta = row.getProductqouta(); double
		 * oldDiscount = row.getProductdiscount(); double oldTotalAfterDiscount =
		 * row.getTotalafterdiscount(); double oldTotaBeforDiscount =
		 * row.getTotalbeforiscount();
		 *
		 * double newpricePerone =
		 * accountatTools.getPriceAfterDiscount(supliserpilldetails.getProductprice(),
		 * supliserpilldetails.getProductdiscount()); double newTotaAfterDisc =
		 * accountatTools.getPriceAfterDiscountForTotalQouta(
		 * supliserpilldetails.getProductprice(),
		 * supliserpilldetails.getProductdiscount(),
		 * supliserpilldetails.getProductqouta()); double newTotaBeforDisc =
		 * accountatTools.getPriceBedforDiscount(supliserpilldetails.getProductprice(),
		 * supliserpilldetails.getProductqouta()); // double supolddebt =
		 * sup.getDebit();
		 *
		 * orderdata.setTotalafterdis(oldTotalAfterDiscount -
		 * orderdata.getTotalafterdis());
		 * orderdata.setTotalbefordis(oldTotaBeforDiscount -
		 * orderdata.getTotalbefordis()); // sup.setDebit(supolddebt-sup.getDebit());
		 *
		 * orderdata.setTotalafterdis(newTotaAfterDisc + orderdata.getTotalafterdis());
		 * orderdata.setTotalbefordis(newTotaBeforDisc + orderdata.getTotalbefordis());
		 * sup.setDebit(oldSupDebit - oldTotalAfterDiscount); sup.setDebit(oldSupDebit +
		 * newTotaAfterDisc);
		 *
		 * row.setProductBounce(supliserpilldetails.getProductBounce());
		 * row.setProductdiscount(supliserpilldetails.getProductdiscount());
		 * row.setProductqouta(supliserpilldetails.getProductqouta());
		 * pillDetailsRepo.save(row); pillRepo.save(orderdata); supRepo.save(sup);
		 *
		 * return pillDetailsRepo.findByPillid(supliserpilldetails.getPillid());
		 */
	}

	public List<supliserpilldetails> updateRow2(supliserpilldetails supliserpilldetails) {

		supliserpilldetails row = pillDetailsRepo.findByPillidAndId(supliserpilldetails.getPillid(),
				supliserpilldetails.getId());
		Optional<supliserpill> orderdata = pillRepo.findById(Long.decode(supliserpilldetails.getPillid()));
		supliser sup = supRepo.findByName(orderdata.get().getSuplisername());
		product prod = prodRepo.findByName(row.getProductname());
		int oldQouta = row.getProductqouta();
		double oldDiscount = row.getProductdiscount();
		double oldTotalAfterDiscount = row.getTotalafterdiscount();
		double oldTotaBeforDiscount = row.getTotalbeforiscount();

		double newpricePerone = accountatTools.getPriceAfterDiscount(supliserpilldetails.getProductprice(),
				supliserpilldetails.getProductdiscount());
		double newTotaAfterDisc = accountatTools.getPriceAfterDiscountForTotalQouta(
				supliserpilldetails.getProductprice(), supliserpilldetails.getProductdiscount(),
				supliserpilldetails.getProductqouta());

		double newTotaBeforDisc = accountatTools.getPriceBedforDiscount(supliserpilldetails.getProductprice(),
				supliserpilldetails.getProductqouta());
		double oldOrderTAfterDis = orderdata.get().getTotalafterdis();
		double oldOrderTBefotDis = orderdata.get().getTotalbefordis();

		double minAfterDis = accountatTools.minOldAndGetNew(oldTotalAfterDiscount, newTotaAfterDisc, oldOrderTAfterDis);
		double minBeforDis = accountatTools.minOldAndGetNew(oldTotaBeforDiscount, newTotaBeforDisc, oldOrderTBefotDis);

		prod.setQouta(Integer.parseInt(String.valueOf(
				accountatTools.minOldAndGetNew(oldQouta, supliserpilldetails.getProductqouta(), prod.getQouta()))));
		sup.setDebit(accountatTools.minOldAndGetNew(row.getTotalafterdiscount(), newTotaAfterDisc, sup.getDebit()));
		orderdata.get().setTotalafterdis(minAfterDis);
		orderdata.get().setTotalbefordis(minBeforDis);
		row.setProductBounce(supliserpilldetails.getProductBounce());
		row.setProductdiscount(supliserpilldetails.getProductdiscount());
		row.setProductqouta(supliserpilldetails.getProductqouta());
		row.setTotalafterdiscount(newTotaAfterDisc);
		row.setTotalbeforiscount(newTotaBeforDisc);
		return pillDetailsRepo.findByPillid(supliserpilldetails.getPillid());

	}

}
