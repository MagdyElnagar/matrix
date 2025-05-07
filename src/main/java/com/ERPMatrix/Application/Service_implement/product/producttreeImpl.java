package com.ERPMatrix.Application.Service_implement.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.product;
import com.ERPMatrix.Application.Model.product.producttree;
import com.ERPMatrix.Application.Repository.product.producttreeRepository;
import com.ERPMatrix.Application.Service.product.producttreeServ;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@javax.transaction.Transactional
public class producttreeImpl implements producttreeServ {

	private prodcutImpl prodcutImpl;
	private producttreeRepository producttreeRepository;

	@Autowired
	public producttreeImpl(producttreeRepository producttreeRepository, prodcutImpl prodcutImpl) {
		super();
		this.producttreeRepository = producttreeRepository;
		this.prodcutImpl = prodcutImpl;
	}

	@Override
	public void delete(producttree producttree) {
		// TODO Auto-generated method stub
		producttreeRepository.delete(producttree);
	}

	@Override
	public List<producttree> findByBenefit(String composite) {
		// TODO Auto-generated method stub
		return producttreeRepository.findByBenefit(composite);
	}

	@Override
	public List<producttree> findByCompany(String company) {
		// TODO Auto-generated method stub
		return producttreeRepository.findByCompany(company);
	}

	@Override
	public List<producttree> findBycomposite(String composite) {
		// TODO Auto-generated method stub
		return producttreeRepository.findBycomposite(composite);
	}

	@Override
	public Optional<producttree> findById(Long prodcutid) {
		// TODO Auto-generated method stub
		return producttreeRepository.findById(prodcutid);
	}

	@Override
	public producttree findByProdcutid(String prodcutid) {
		// TODO Auto-generated method stub
		return producttreeRepository.findByProdcutid(prodcutid);
	}

	@Override
	public List<producttree> findBySection(String composite) {
		// TODO Auto-generated method stub
		return producttreeRepository.findBySection(composite);
	}

	@Override
	public List<producttree> findByType(String type) {
		// TODO Auto-generated method stub
		return producttreeRepository.findByType(type);
	}

	@Override
	public producttree save(producttree producttree) throws HandlerException {

		Optional<product> pro = prodcutImpl.findById(Long.decode(producttree.getProdcutid()));

		if (producttreeRepository.findByProdcutid(String.valueOf(pro.get().getId())) == null) {

			return producttreeRepository.save(producttree);

		} else {

			throw new HandlerException("هذا الصنف له بيانات ", "producttreeImpl save");

		}

	}

}
