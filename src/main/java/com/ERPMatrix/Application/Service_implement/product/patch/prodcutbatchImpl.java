package com.ERPMatrix.Application.Service_implement.product.patch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.productbatch;
import com.ERPMatrix.Application.Model.product.store;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Repository.product.productbatchRepository;
import com.ERPMatrix.Application.Service.product.productbatchServ;
import com.ERPMatrix.Application.Service.product.storeServ;
import com.ERPMatrix.Application.Service_implement.product.prodcutImpl;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
public class prodcutbatchImpl implements productbatchServ {

	private AccountatTools accTools;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	private prodcutImpl prodcutImpl;
	private productbatchRepository productbatchRepository;
	private storeServ storeServ;

	@Autowired
	public prodcutbatchImpl(prodcutImpl prodcutImpl, productbatchRepository productbatchRepository,
			storeServ storeServ) {
		super();
		this.prodcutImpl = prodcutImpl;
		this.productbatchRepository = productbatchRepository;
		this.storeServ = storeServ;
	}

	@Override
	public productbatch add(productbatch productbatch, supliserpilldetails pilldetail) throws HandlerException {
		productbatch prodFind = productbatchRepository.findByProductnameAndBatchAndPillid(productbatch.getProductname(),
				productbatch.getBatch(), productbatch.getPillid());

		List<store> st = storeServ.findAll();
		List<productbatch> patchs = productbatchRepository.findByProductnameAndPillid(productbatch.getProductname(),
				productbatch.getPillid());

		product pro = prodcutImpl.findByName(productbatch.getProductname());
		productbatch.setEntrydate(new Date());
		productbatch.setProductid(String.valueOf(pro.getId()));
		productbatch.setProductprice(pilldetail.getProductprice());
		productbatch.setProductdiscount(pilldetail.getProductdiscount());
		if (productbatch.getStore() == null || productbatch.getStore().equals("")) {
			productbatch.setStore(st.get(0).getName());
		}

		int product_Get_All_Patch_QTY = 0;

		if (patchs.size() > 0) {

			for (int z = 0; z < patchs.size(); z++) {
				if (patchs.get(z).getBatch() != null) {
					product_Get_All_Patch_QTY = product_Get_All_Patch_QTY + patchs.get(z).getProductqouta();
				}

				if (productbatch.getBatch().equals(patchs.get(z).getBatch())) {
					throw new HandlerException("هذا الباتش موجود بالفعل", "productbatch add class");
				}

			}

			if (product_Get_All_Patch_QTY >= pilldetail.getProductqouta()) {
				throw new HandlerException("الكمية المدخلة اكبر من كمية الصنف فى الفاتورة", "productbatch add class");
			} else {
				if (productbatch.getProductqouta() == pilldetail.getProductqouta()) {
					productbatchRepository.deleteById(patchs.get(0).getId());

					productbatchRepository.save(productbatch);

				} else {

					if (patchs.size() > 0) {
						if (patchs.get(0).getBatch() != null) {

							throw new HandlerException("لا يمكن إضافة المذيد من الباتشات", "productbatch add class");
						}
					} else {

						if (patchs.get(0).getProductqouta() == productbatch.getProductqouta()
								|| patchs.get(0).getBatch() != null) {
							patchs.get(0).setBatch(productbatch.getBatch());
							patchs.get(0).setExpire(productbatch.getExpire());

							productbatchRepository.deleteById(patchs.get(0).getId());

							productbatchRepository.save(patchs.get(0));

						} else {
							productbatch newRow = new productbatch();
							newRow.setProductname(productbatch.getProductname());
							newRow.setBatch(productbatch.getBatch());
							newRow.setEntrydate(new Date());
							newRow.setExpire(productbatch.getExpire());
							newRow.setPillid(productbatch.getPillid());
							newRow.setProductdiscount(pilldetail.getProductdiscount());
							newRow.setProductid(String.valueOf(pro.getId()));
							newRow.setProductprice(pro.getPrice());
							newRow.setProductqouta(productbatch.getProductqouta());
							newRow.setStore(productbatch.getStore());
							newRow.setSupliser(productbatch.getSupliser());
							patchs.get(0)
									.setProductqouta(patchs.get(0).getProductqouta() - productbatch.getProductqouta());
							productbatchRepository.save(newRow);

							if (patchs.get(0).getProductqouta() == 0) {

								productbatchRepository.deleteById(patchs.get(0).getId());

							} else {

								productbatchRepository.save(patchs.get(0));

							}
							// updatePatchesDiscount(productbatch.getProductname(),
							// productbatch.getProductprice());
							return null;

						}
					}

				}
			}
		}else {
			
			productbatch newRow = new productbatch();
			newRow.setProductname(productbatch.getProductname());
			newRow.setBatch(productbatch.getBatch());
			newRow.setEntrydate(new Date());
			newRow.setExpire(productbatch.getExpire());
			newRow.setPillid(productbatch.getPillid());
			newRow.setProductdiscount(pilldetail.getProductdiscount());
			newRow.setProductid(String.valueOf(pro.getId()));
			newRow.setProductprice(pro.getPrice());
			newRow.setProductqouta(productbatch.getProductqouta());
			newRow.setStore(productbatch.getStore());
			newRow.setSupliser(productbatch.getSupliser());
		
			productbatchRepository.save(newRow);
			
		}
		return null;

	}

	@Override
	public void deleteBatch(Long id) throws HandlerException {

		Optional<productbatch> prod = productbatchRepository.findById(id);

		if (prod == null) {

			throw new HandlerException("لا يوجد بيانات لهذه التشغيلة", "prodcut patch impl deleteBatch");

		} else {

			if (prod.get().getProductqouta() > 0) {
				throw new HandlerException("لا يمكن حذف هذه التشغيله يوجد كميه فى المخزن من هذه التشغيله",
						"prodcut patch impl deleteBatch");

			} else {
				Date date = new Date();
				SimpleDateFormat dfm = new SimpleDateFormat("YYYY");
				int prodDate = Integer.parseInt(dfm.format(prod.get().getEntrydate()));
				int date_now = Integer.parseInt(dfm.format(date));

				if (prodDate < date_now) {
					productbatchRepository.deleteById(id);
				} else {
					throw new HandlerException("هذا الباتش لم يكمل العام", "prodcut patch impl deleteBatch");
				}

			}
		}

	}

	@Override
	public List<productbatch> deleteFromPill(Long id) {
		Optional<productbatch> prod = productbatchRepository.findById(id);

		productbatchRepository.deleteById(id);
		List<productbatch> patches = findByProductnameAndPillid(prod.get().getProductname(), prod.get().getPillid());

		if (patches.size() > 1) {
			int fake_rows = 0;
			int fake_QTY = 0;
			for (int z = 0; z < patches.size(); z++) {

				if (patches.get(z).getBatch() == null) {
					fake_rows = fake_rows + 1;
				}
			}

			if (fake_rows > 1) {

				for (int z = 0; z < patches.size(); z++) {

					if (patches.get(z).getBatch() == null) {

						fake_QTY = fake_QTY + patches.get(z).getProductqouta();
						productbatchRepository.deleteById(patches.get(z).getId());
					}

				}

			}

		}

		return productbatchRepository.findByProductnameAndPillid(prod.get().getProductname(), prod.get().getPillid());
	}

	@Override
	public List<productbatch> findByBatch(String batch) {
		return productbatchRepository.findByBatch(batch);
	}

	@Override
	public List<productbatch> findByDateBetweenDate(Date fDate, Date SDate) {

		return productbatchRepository.findByEntrydateBetween(fDate, SDate);
	}

	@Override
	public List<productbatch> findByExpire(Date date) {
		return productbatchRepository.findByEntrydateLessThanEqual(new Date());

	}

	@Override
	public Optional<productbatch> findById(Long id) {
		return productbatchRepository.findById(id);
	}

	@Override
	public product findByNameAndStatusAndStore(String name, String store) throws HandlerException {
		List<productbatch> Patches = productbatchRepository.findByProductnameAndStoreAndProductqoutaGreaterThan(name,
				store, 0);
		LOGGER.info("name = " + name);
		LOGGER.info("store = " + store);
		int qty = 0;

		if (Patches == null) {
			LOGGER.info("QTY = " + Patches.size());
			throw new HandlerException("لا يوجد كمية من هذا الصنف",
					"product patch impl  >>>>>findByNameAndStatusAndStore");

		} else {

			if (Patches.size() > 0) {
				LOGGER.info("Patches.size() > 0 ");

				for (int x = 0; x < Patches.size(); x++) {
					LOGGER.info("QTY = for " + Patches.get(x).getProductqouta());

					qty = qty + Patches.get(x).getProductqouta();
				}

			} else {

				LOGGER.info("Patches.size() < 0 ");
				LOGGER.info("QTY = " + Patches.size());
				throw new HandlerException("لا يوجد كمية من هذا الصنف",
						"product patch impl  >>>>> findByNameAndStatusAndStore");

			}
			LOGGER.info("QTY = " + qty);

			product product = new product();
			product = prodcutImpl.findByName(name);
			product.setQouta(qty);

			return product;
		}
	}

	@Override
	public List<productbatch> findByPillid(String pillid) {

		return productbatchRepository.findByPillid(pillid);
	}

	@Override
	public productbatch findByPillidAndProductname(String product, String pillid) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByPillidAndProductname(product, pillid);
	}

	@Override
	public List<productbatch> findByProductid(String productid) {

		return productbatchRepository.findByProductid(productid);
	}

	@Override
	public List<productbatch> findByProductidAndProductqoutaGreaterThan(String productid, int qouta) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByProductidAndProductqoutaGreaterThan(productid, qouta);

	}

	@Override
	public List<productbatch> findByProductname(String productname) throws HandlerException {
		List<productbatch> prodFind = productbatchRepository.findByProductname(productname);
		if (prodFind == null) {
			throw new HandlerException("لا يوجد بتشات لهذا الصنف", "prodcut patch impl findByProductname");
		} else {
			return productbatchRepository.findByProductname(productname);
		}
	}

	@Override
	public List<productbatch> findByProductnameAndPillid(String product, String pillid) {
		// TODO Auto-generated method stub
		LOGGER.info("P " + product + " : " + pillid);
		return productbatchRepository.findByProductnameAndPillid(product, pillid);
	}

	@Override
	public productbatch findByProductnameAndProductpriceAndBatch(String name, double price, String batch) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByProductnameAndProductpriceAndBatch(name, price, batch);
	}

	@Override
	public List<productbatch> findByProductnameAndProductpriceAndBatchIfPatchEqualNull(String name, double price,
			String batch) {

		return productbatchRepository.findByProductpriceAndProductnameAndBatch(price, name, batch);
	}

	@Override
	public List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThan(String Productname,
			double price, int qty) {

		return productbatchRepository.findByProductnameAndProductpriceAndProductqoutaGreaterThan(Productname, price,
				qty);
	}

	@Override
	public List<productbatch> findByProductnameAndProductpriceAndProductqoutaGreaterThanOrderByExpire(String proname,
			double price, int qouta) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByProductnameAndProductpriceAndProductqoutaGreaterThanOrderByExpire(proname,
				price, qouta);
	}

	@Override
	public List<productbatch> findByProductnameAndProductqoutaGreaterThanOrderByExpire(String proname, int qouta) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByProductnameAndProductqoutaGreaterThanOrderByExpire(proname, qouta);
	}

	@Override
	public List<productbatch> findByProductnameAndStoreAndProductpriceAndProductqoutaGreaterThanOrderByExpire(
			String Productname, String sotre, double price, int qouta) {
		// TODO Auto-generated method stub
		return productbatchRepository.findByProductnameAndStoreAndProductpriceAndProductqoutaGreaterThanOrderByExpire(
				Productname, sotre, price, qouta);
	}

	@Override
	public List<productbatch> findByProductnameOrderByExpire(String proname) {

		return productbatchRepository.findByProductnameOrderByExpire(proname);

	}

	@Override
	public List<productbatch> getFullDetailsForProduct(String name) {

		List<productbatch> firstJop = productbatchRepository
				.findByProductnameAndProductqoutaGreaterThanOrderByExpire(name, 0);

		List<productbatch> SecondJop = new ArrayList<productbatch>();

		for (int x = 0; x < firstJop.size(); x++) {

			if (SecondJop.size() <= 0) {
				SecondJop.add(firstJop.get(x));

			} else {
				boolean cheak = false;
				int getInd = 0;

				for (int i = 0; i < SecondJop.size(); i++) {

					if (SecondJop.get(i).getStore().equals(firstJop.get(x).getStore())) {
						cheak = true;
						getInd = i;

					} else {

						// cheak = false;
					}

				}

				if (cheak) {
					SecondJop.get(getInd).setProductqouta(
							firstJop.get(x).getProductqouta() + SecondJop.get(getInd).getProductqouta());

				} else {

					SecondJop.add(firstJop.get(x));

				}

			}

		}

		return SecondJop;
	}

	@Override
	public productbatch minToProduct(productbatch productbatch) {
		List<productbatch> oldPatch = findByProductnameAndPillid(productbatch.getProductname(),
				productbatch.getPillid());

		for (int x = 0; x < oldPatch.size(); x++) {
			productbatchRepository.deleteById(oldPatch.get(x).getId());

		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public productbatch plusToProduct(productbatch productbatch) {
		List<productbatch> oldPatch = findByProductnameAndPillid(productbatch.getProductname(),
				productbatch.getPillid());

		if (oldPatch == null) {

		} else {

		}

		return null;
	}

	@Override
	public productbatch save(productbatch productbatch) throws HandlerException {

		productbatch prodFind = productbatchRepository.findByProductnameAndBatch(productbatch.getProductname(),
				productbatch.getBatch());
		LOGGER.warn("Product Patch impl save " + productbatch.getProductname());
		product pro = prodcutImpl.findByName(productbatch.getProductname());
		productbatch.setEntrydate(new Date());

		productbatch.setProductid(pro.getId().toString());
		productbatch.setProductprice(pro.getPrice());

		return productbatchRepository.save(productbatch);

	}

	@Override
	public productbatch update(productbatch productbatch) throws HandlerException {

		productbatch prodFind = productbatchRepository.findByProductnameAndBatch(productbatch.getProductname(),
				productbatch.getBatch());
		if (prodFind == null) {
			throw new HandlerException("لا يوجد بيانات لهذه التشغيلة", "prodcut patch impl update");

		} else {

			return productbatchRepository.save(productbatch);

		}

	}

	@Override
	public productbatch updatePatch(productbatch productbatch) {
		// TODO Auto-generated method stub
		return productbatchRepository.save(productbatch);
	}

	@Override
	public void updatePatchesDiscount(String proname, double price) throws HandlerException {
		LOGGER.info("updatePatchesDiscount");

		List<productbatch> Patches = findByProductnameAndProductpriceAndProductqoutaGreaterThan(proname, price, 0);

		product pro = prodcutImpl.findByName(proname);

		if (Patches.size() == 0) {
			LOGGER.info("Patches == null");

			pro.setQouta(0);
			// pro.setWeigheddiscount(0);

		} else if (Patches.size() > 0) {
			LOGGER.info("Patches !== null");

			double totaAmount = 0;
			int totalQTY = 0;
			for (int x = 0; x < Patches.size(); x++) {

				double endPrice = price - price / 100 * Patches.get(x).getProductdiscount();
				endPrice = endPrice * Patches.get(x).getProductqouta();

				totaAmount = totaAmount + endPrice;

				totalQTY = totalQTY + Patches.get(x).getProductqouta();
			}

			double productCost = totaAmount / totalQTY;

			double Discount = (1 - productCost / price) * 100;

			pro.setWeigheddiscount(Discount);
			pro.setQouta(totalQTY);

		}
		/*
		 *
		 * LOGGER.info(pro.getName()); LOGGER.info(String.valueOf(pro.getPrice()));
		 * LOGGER.info(String.valueOf(pro.getQouta()));
		 * LOGGER.info(String.valueOf(pro.getWeigheddiscount()));
		 *
		 */
		prodcutImpl.update(pro);

	}

}
