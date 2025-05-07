package com.ERPMatrix.Application.Service_implement.delegate;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.delegate.delegate;
import com.ERPMatrix.Application.Repository.delegate.delegate_repository;
import com.ERPMatrix.Application.Service.delegate.delegate_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class delegate_Implement implements delegate_service {

	@Autowired
	private delegate_repository delegate_repo;

	@Override
	public delegate delete(delegate delegate) {
		// TODO Auto-generated method stub
		delegate_repo.deleteById(delegate.getId());
		return null;
	}

	@Override
	public List<delegate> findAll() {
		// TODO Auto-generated method stub
		return delegate_repo.findAll();
	}

	@Override
	public delegate findByGov(String Gov) {
		// TODO Auto-generated method stub
		return delegate_repo.findByGov(Gov);
	}

	@Override
	public delegate findByName(String name) {
		// TODO Auto-generated method stub
		return delegate_repo.findByName(name);
	}

	@Override
	public delegate findByTraficline(String traficline) {
		// TODO Auto-generated method stub
		return delegate_repo.findByTraficline(traficline);
	}

	@Override
	public delegate save(delegate delegate) throws HandlerException {
		delegate del = findByName(delegate.getName());
		if (!(del == null)) {
			throw new HandlerException("هذا المندوب موجود بالفعل", "delegate_Implement >>>> Save Method");
		}

		return delegate_repo.save(delegate);
	}

	@Override
	public delegate update(delegate delegate) {
		delegate del = findByName(delegate.getName());
		delegate.setId(del.getId());

		return delegate_repo.save(delegate);
	}

}
