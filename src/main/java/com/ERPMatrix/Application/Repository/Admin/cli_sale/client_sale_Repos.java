package com.ERPMatrix.Application.Repository.Admin.cli_sale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.client_sale.model.client_sale;

@Repository
public interface client_sale_Repos extends JpaRepository<client_sale, Long> {

	List<client_sale> findByTelesalse(String Sales);

	client_sale findByTelesalseAndClient(String sales, String cli);

	List<client_sale> findByTelesalseOrderByCalltime(String Sales);

}
