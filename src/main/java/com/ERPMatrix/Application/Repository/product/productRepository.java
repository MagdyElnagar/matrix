package com.ERPMatrix.Application.Repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.product.product;

@Repository
public interface productRepository extends JpaRepository<product, Long> {

	List<product> findAllByOrderByName();

	product findByName(String name);

	List<product> findByNameIsContaining(String product);

	List<product> findByQouta(int Qouta);

	List<product> findByQoutaGreaterThan(int Qouta);

	List<product> findByQoutaGreaterThanOrderByName(int Qouta);

	List<product> findByStatus(boolean status);
}
