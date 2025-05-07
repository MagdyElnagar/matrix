package com.ERPMatrix.Application.Repository.delegate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.trafic.traficModel;

@Repository
public interface traficRepo extends JpaRepository<traficModel, Long> {

}
