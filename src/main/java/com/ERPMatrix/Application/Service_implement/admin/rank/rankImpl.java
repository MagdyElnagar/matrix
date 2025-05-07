package com.ERPMatrix.Application.Service_implement.admin.rank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.admin.rank.rankmodel;
import com.ERPMatrix.Application.Repository.Admin.rank.RankRepo;
import com.ERPMatrix.Application.Service.admin.rank.rankServ;

@Service
public class rankImpl implements rankServ {

	@Autowired
	private RankRepo rankRepo;

	@Override
	public void delete(rankmodel rankmodel) {
		rankRepo.deleteById(rankmodel.getId());

	}

	@Override
	public List<rankmodel> findAll() {
		// TODO Auto-generated method stub
		return rankRepo.findAll();
	}

	@Override
	public rankmodel findByName(String name) {
		// TODO Auto-generated method stub
		System.out.println("Ranks ");
		return rankRepo.findByName(name);
	}

	@Override
	public rankmodel Save(rankmodel rankmodel) {

		rankmodel find = rankRepo.findByName(rankmodel.getName());
		if (find == null) {
			return rankRepo.save(rankmodel);

		} else {
			rankmodel.setId(find.getId());
			return rankRepo.save(rankmodel);

		}
		// TODO Auto-generated method stub
	}

}
