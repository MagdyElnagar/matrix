package com.ERPMatrix.Application.Service_implement.product;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.store;
import com.ERPMatrix.Application.Repository.product.storeRepository;
import com.ERPMatrix.Application.Service.product.storeServ;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class storeImpl implements storeServ {

	@Autowired
	storeRepository storeRepo;

	@Override
	public void deletestore(store store) {

		storeRepo.deleteById(store.getId());
	}

	@Override
	public List<store> findAll() {
		// TODO Auto-generated method stub
		return storeRepo.findAll();
	}

	@Override
	public Optional<store> findById(Long id) {
		// TODO Auto-generated method stub
		return storeRepo.findById(id);
	}

	@Override
	public store findByName(String name) {
		// TODO Auto-generated method stub
		return storeRepo.findByName(name);
	}

	@Override
	public List<store> findByProductid(String productid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<store> findByProductname(String Productname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public store save(store store) throws HandlerException {
		store st = storeRepo.findByName(store.getName());

		if (st == null) {

			return storeRepo.save(store);

		} else {
			throw new HandlerException("هذا المخزن موجود بالفعل", "StoreImpl save");
		}
	}

}
