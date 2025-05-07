package com.ERPMatrix.Application.Repository.delegate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.delegate.delegate;

@Repository
public interface delegate_repository extends JpaRepository<delegate, Long> {
	delegate findByGov(String Gov);

	delegate findByName(String name);

	delegate findByTraficline(String traficline);
}
