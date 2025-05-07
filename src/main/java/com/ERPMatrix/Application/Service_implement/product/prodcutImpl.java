package com.ERPMatrix.Application.Service_implement.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Repository.product.productRepository;
import com.ERPMatrix.Application.Service.product.productServ;
import com.ERPMatrix.Application.StaticMethod.AccountatTools;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service

public class prodcutImpl implements productServ {

	private AccountatTools accTools;
	private productRepository productRepository;

	@Autowired
	public prodcutImpl(com.ERPMatrix.Application.Repository.product.productRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<product> findAll() {
		return productRepository.findAllByOrderByName();
	}

	@Override
	public List<product> findByGreatherthan(int qouta) {
		return productRepository.findByQoutaGreaterThanOrderByName(0);
	}

	@Override
	public Optional<product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public product findByName(String name) throws HandlerException {
		product pro = productRepository.findByName(name);

		if (pro == null) {
			throw new HandlerException("هذا الاسم غير موجود : ", "product impl findByName class");
		} else {

			return pro;

		}

	}

	@Override
	public List<product> findByNameIsContaining(String product) {

		return productRepository.findByNameIsContaining(product);
	}

	@Override
	public List<product> findByStatus(boolean status) {
		// TODO Auto-generated method stub
		return productRepository.findByStatus(status);
	}

	@Override
	public List<product> findByZeroQouta(int qouta) {

		return productRepository.findByQouta(0);
	}

	@Override
	public product minFromProduct(String name, int qty) throws HandlerException {

		product pro = findByName(name);

		return findByName(name);
	}

	@Override
	public product plusToProduct(String name, int qty) throws HandlerException {
		product pro = findByName(name);

		int oldQTY = pro.getQouta();
		int last = oldQTY - qty;

		pro.setQouta(last);

		return findByName(name);
	}

	@Override
	public product Save(product product) throws HandlerException {
		product prod = productRepository.findByName(product.getName());

		if (!(prod == null)) {
			throw new HandlerException("هذه البيانات موجودة بالفعل", "product impl save");

		} else {
			return productRepository.save(product);

		}

	}

	@Override
	public product update(product product) throws HandlerException {

		Optional<product> prod = productRepository.findById(product.getId());
		if (prod == null) {

			throw new HandlerException("لا يوجد بيانات لهذا الصنف", "product impl update");
		} else {

			return productRepository.save(product);

		}

	}

}
