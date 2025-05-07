package com.ERPMatrix.Application.Repository.File;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.compare.compareModel1;



@Repository
public interface compare1 extends JpaRepository<compareModel1, Long> {

	
	List<compareModel1> findByNameLike(String name);
	
}
