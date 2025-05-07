package com.ERPMatrix.Application.Service.product;

import java.util.List;
import java.util.Optional;

import com.ERPMatrix.Application.Model.product.producttree;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface producttreeServ {

	Optional<producttree> findById(Long prodcutid);
	producttree findByProdcutid(String prodcutid);
	producttree save(producttree producttree) throws HandlerException;
	void delete(producttree producttree);
	List<producttree> findByCompany(String company);
	List<producttree> findByType(String type);
	List<producttree> findBycomposite(String composite);
	List<producttree> findByBenefit(String composite);
	List<producttree> findBySection(String composite);


}
