package com.ERPMatrix.Application.Repository.Admin.rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.admin.rank.rankmodel;

@Repository
public interface RankRepo extends JpaRepository<rankmodel, Long> {

	rankmodel findByName(String name);

}
