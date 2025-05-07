package com.ERPMatrix.Application.Service.delegate;

import java.util.List;

import com.ERPMatrix.Application.Model.delegate.delegate;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface delegate_service {

	delegate delete(delegate delegate);

	List<delegate> findAll();

	delegate findByGov(String Gov);

	delegate findByName(String name);

	delegate findByTraficline(String traficline);

	delegate save(delegate delegate) throws HandlerException;

	delegate update(delegate delegate);

}
