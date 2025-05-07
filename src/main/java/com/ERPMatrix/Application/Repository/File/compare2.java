package com.ERPMatrix.Application.Repository.File;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ERPMatrix.Application.Model.compare.compareModel2;

public interface compare2  extends JpaRepository<compareModel2, Long> {
	List<compareModel2> findByNameLike(String name);
}
