package com.ERPMatrix.Application.Repository.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.admin.Data_Base_BackUp;

@Repository
public interface database_backup extends JpaRepository<Data_Base_BackUp, Long> {

}
