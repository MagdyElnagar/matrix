package com.ERPMatrix.Application.Repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.product.store;

@Repository
public interface storeRepository extends JpaRepository<store, Long> {

	@Override
	void deleteById(Long id);

	@Override
	List<store> findAll();

	store findByName(String name);

	@Override
	store save(store store);
}
