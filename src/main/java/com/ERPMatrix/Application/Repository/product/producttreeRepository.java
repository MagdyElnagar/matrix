package com.ERPMatrix.Application.Repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ERPMatrix.Application.Model.product.producttree;
@Repository
public interface producttreeRepository extends JpaRepository<producttree,Long> {

	producttree findByProdcutid(String prodcutid);
	List<producttree> findByCompany(String company);
	List<producttree> findByType(String type);
	List<producttree> findBycomposite(String composite);
	List<producttree> findByBenefit(String composite);
	List<producttree> findBySection(String composite);
	void delete(producttree producttree);

	
}
