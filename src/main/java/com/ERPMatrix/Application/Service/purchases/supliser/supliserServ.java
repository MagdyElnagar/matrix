package com.ERPMatrix.Application.Service.purchases.supliser;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ERPMatrix.Application.Model.finance.Accountat.staticResult.AccountatSaleMonitoring;
import com.ERPMatrix.Application.Model.purchases.Supliser.supliser;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface supliserServ {

	List<supliser> findAll();

	supliser findSup(supliser supliser);

	supliser findSupByName(String supliser);

	supliser updatesupliser(supliser supliser);

	supliser savesupliser(supliser supliser) throws HandlerException;

	List<supliser> findByStatus(Boolean status);

	AccountatSaleMonitoring supliserAccountMoitor(String suplisername, String datefrom, String dateto)
			throws HandlerException, ParseException;

}
