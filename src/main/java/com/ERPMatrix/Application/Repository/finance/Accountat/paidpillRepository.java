package com.ERPMatrix.Application.Repository.finance.Accountat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.finance.Accountat.paidpill;

@Repository
public interface paidpillRepository extends JpaRepository<paidpill, Long> {

}
