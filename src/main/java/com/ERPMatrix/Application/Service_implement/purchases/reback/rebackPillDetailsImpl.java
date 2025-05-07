package com.ERPMatrix.Application.Service_implement.purchases.reback;

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
import com.ERPMatrix.Application.Model.purchases.Reback.rebackPillModel;
import com.ERPMatrix.Application.Model.purchases.Reback.rebakDetailsModel;
import com.ERPMatrix.Application.Model.purchases.Supliser.DepitHistory;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.Repository.product.productRepository;
import com.ERPMatrix.Application.Repository.purchases.rebak.rebackPillDetailsRepo;
import com.ERPMatrix.Application.Repository.purchases.rebak.rebackPillRepos;
import com.ERPMatrix.Application.Repository.purchases.supliser.supliserRepository;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespillServ;
import com.ERPMatrix.Application.Service.purchases.pill.salespilldetailsServ;
import com.ERPMatrix.Application.Service.purchases.reback.rebackPillDetailsServ;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class rebackPillDetailsImpl implements rebackPillDetailsServ {

	private AccountatTools accTools;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	private productbatchServ patchServ;
	private salespilldetailsServ pillDetails;
	private productRepository productRepo;
	@Autowired

	private rebackPillDetailsRepo rebackPillDetailsRepo;
	private rebackPillRepos rebackpillrepo;
	private salespillServ salespillServ;
	private supliserRepository supliserRepo;

	@Autowired
	public rebackPillDetailsImpl(AccountatTools accTools,
			com.ERPMatrix.Application.Repository.purchases.supliser.depitHistoryRepo depitHistoryRepo,
			productbatchServ patchServ, salespilldetailsServ pillDetails,
			com.ERPMatrix.Application.Service.purchases.pill.salespillServ salespillServ, productRepository productRepo,
			com.ERPMatrix.Application.Repository.purchases.rebak.rebackPillDetailsRepo rebackPillDetailsRepo,
			rebackPillRepos rebackpillrepo, supliserRepository supliserRepo) {
		super();
		this.accTools = accTools;
		this.patchServ = patchServ;
		this.pillDetails = pillDetails;
		this.salespillServ = salespillServ;
		this.productRepo = productRepo;
		this.rebackPillDetailsRepo = rebackPillDetailsRepo;
		this.rebackpillrepo = rebackpillrepo;
		this.supliserRepo = supliserRepo;
	}

	private void addNewRow(rebakDetailsModel rebakDetailsModel) throws HandlerException {

		product prod = productRepo.findByName(rebakDetailsModel.getProductname());
		Optional<rebackPillModel> rebackPill = rebackpillrepo.findById(Long.parseLong(rebakDetailsModel.getRebackid()));

		List<productbatch> patchs = patchServ.findByProductnameAndPillid(prod.getName(), rebackPill.get().getPillid());

		for (int i = 0; i < patchs.size(); i++) {

			if (i > 1) {
				patchServ.deleteBatch(patchs.get(i).getId());

			}
			patchs.get(i).setProductqouta(0);
			patchServ.updatePatch(patchs.get(i));

		}
		rebakDetailsModel.setDate(new Date());
		rebakDetailsModel.setSupname(rebackPill.get().getSuplisername());
		supliser sup = supliserRepo.findByName(rebackPill.get().getSuplisername());

		Optional<supliserpill> pill = salespillServ.findById(Long.parseLong(rebackPill.get().getPillid()));
		supliserpilldetails pilldetails = pillDetails.findByProductAndPillId(rebakDetailsModel.getProductname(),
				rebackPill.get().getPillid());

		double totalAfterDiscount = accTools.getPriceAfterDiscountForTotalQouta(pilldetails.getProductprice(),
				pilldetails.getProductdiscount(), pilldetails.getProductqouta());
		double supDebit = sup.getDebit() - totalAfterDiscount;

		sup.setDebit(supDebit);
		prod.setQouta(prod.getQouta() - pilldetails.getProductqouta());

		rebackPill.get().setPrice(rebackPill.get().getPrice() + totalAfterDiscount);

		rebackpillrepo.save(rebackPill.get());
		supliserRepo.save(sup);
		rebackPillDetailsRepo.save(rebakDetailsModel);
		patchServ.updatePatchesDiscount(prod.getName(), pilldetails.getProductprice());

	}

	@Override
	public List<rebakDetailsModel> deleteRow(rebakDetailsModel rebakDetailsModel) throws HandlerException {
		product prod = productRepo.findByName(rebakDetailsModel.getProductname());
		Optional<rebackPillModel> rebackPill = rebackpillrepo.findById(Long.parseLong(rebakDetailsModel.getRebackid()));
		List<productbatch> proPatch = patchServ.findByProductnameAndPillid(prod.getName(),
				rebackPill.get().getPillid());
		supliser sup = supliserRepo.findByName(rebackPill.get().getSuplisername());
		Optional<supliserpill> pill = salespillServ.findById(Long.parseLong(rebackPill.get().getPillid()));
		supliserpilldetails pilldetails = pillDetails.findByProductAndPillId(rebakDetailsModel.getProductname(),
				rebackPill.get().getPillid());
		double totalAfterDiscount = accTools.getPriceAfterDiscountForTotalQouta(pilldetails.getProductprice(),
				pilldetails.getProductdiscount(), pilldetails.getProductqouta());
		double supDebit = sup.getDebit() + totalAfterDiscount;
		sup.setDebit(supDebit);
		prod.setQouta(prod.getQouta() + pilldetails.getProductqouta());
		proPatch.get(0).setProductqouta(pilldetails.getProductqouta());
		rebackPill.get().setPrice(rebackPill.get().getPrice() - totalAfterDiscount);

		rebackpillrepo.save(rebackPill.get());
		supliserRepo.save(sup);
		patchServ.update(proPatch.get(0));
		patchServ.updatePatchesDiscount(prod.getName(), prod.getPrice());
		rebackPillDetailsRepo.delete(rebakDetailsModel);

		List<rebakDetailsModel> last = findByRebackid(String.valueOf(rebackPill.get().getId()));

		if (last.size() <= 0) {
			LOGGER.info("last.size()  =<0 ");

			return null;
		} else {
			LOGGER.info("last.size()  >0 ");

			return last;

		}

	}

	@Override
	public List<rebakDetailsModel> findAll() {
		// TODO Auto-generated method stub
		return rebackPillDetailsRepo.findAll();
	}

	@Override
	public Optional<rebakDetailsModel> findById(Long id) {
		// TODO Auto-generated method stub
		return rebackPillDetailsRepo.findById(id);
	}

	@Override
	public List<rebakDetailsModel> findByPillid(String pillid) {
		rebackPillModel pill = rebackpillrepo.findByPillid(pillid);

		return rebackPillDetailsRepo.findByRebackid(String.valueOf(pill.getId()));
	}

	@Override
	public List<rebakDetailsModel> findByProduct(String productname) {
		// TODO Auto-generated method stub
		return rebackPillDetailsRepo.findByProductname(productname);
	}

	@Override
	public rebakDetailsModel findByProductnameAndRebackid(String productname, String Rebackid) {
		// TODO Auto-generated method stub
		return rebackPillDetailsRepo.findByProductnameAndRebackid(productname, Rebackid);
	}

	@Override
	public List<rebakDetailsModel> findByRebackid(String Rebackid) {
		// TODO Auto-generated method stub
		return rebackPillDetailsRepo.findByRebackid(Rebackid);
	}

	@Override
	public rebakDetailsModel save(rebakDetailsModel rebakDetailsModel) throws HandlerException {
		addNewRow(rebakDetailsModel);

		return rebakDetailsModel;

	}

	private void updateRow(char opt, rebakDetailsModel rebakDetailsModel, rebackPillModel rebackPillModel,
			supliser supliser, DepitHistory depithistory) throws HandlerException {

		product prod = productRepo.findByName(rebakDetailsModel.getProductname());

		// productbatch proPatch = patchServ.findByProductnameAndPillid(prod.getName(),
		// rebackPillModel.getPillid());

		// proPatch.setProductqouta(0);
		// patchServ.update(proPatch);
		List<productbatch> productPatches = patchServ.findByProductidAndProductqoutaGreaterThan(prod.getId().toString(),
				0);

		double priceAfterDis = accTools.getPriceAfterDiscountForTotalQouta(rebakDetailsModel.getProductprice(),
				rebakDetailsModel.getDiscount(), rebakDetailsModel.getProductqouta());

		System.out.println("opt : " + opt);

		if (opt == '+') {
			supliser.setDebit(accTools.minThis(supliser.getDebit(), priceAfterDis));
			rebackPillModel.setPrice(rebackPillModel.getPrice() + priceAfterDis);

			prod.setQouta(prod.getQouta() - rebakDetailsModel.getProductqouta());

		} else {

			supliser.setDebit(accTools.minThis(supliser.getDebit(), priceAfterDis));
			rebackPillModel.setPrice(rebackPillModel.getPrice() + rebakDetailsModel.getTotalafterdiscount());

			prod.setQouta(prod.getQouta() - rebakDetailsModel.getProductqouta());
			rebakDetailsModel.setTotalafterdiscount(priceAfterDis);

		}

		rebackpillrepo.save(rebackPillModel);
		productRepo.save(prod);
		supliserRepo.save(supliser);

	}

}
