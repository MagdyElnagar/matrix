package com.ERPMatrix.Application.Repository.purchases.supliser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;

@Repository
public interface supliserRepository extends JpaRepository<supliser, Long> {

	List<supliser> findByStatus(Boolean status);

	supliser findByName(String name);

}
