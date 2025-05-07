package com.ERPMatrix.Application.Repository.Client;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.Client.client;

@Repository
@Transactional
public interface clientRepository extends JpaRepository<client, Long> {

	List<client> findAllByOrderByName();

	client findByCommercialregister(String Commercialregister);

	client findByName(String name);

	List<client> findByStatusTrueOrderByName();

}
