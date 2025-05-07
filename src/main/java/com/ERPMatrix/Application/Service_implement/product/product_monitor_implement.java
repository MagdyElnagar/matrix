package com.ERPMatrix.Application.Service_implement.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.product.view.P_purchess_reback_view;
import com.ERPMatrix.Application.Model.product.view.P_purchess_view;
import com.ERPMatrix.Application.Model.product.view.P_sales_reback_view;
import com.ERPMatrix.Application.Model.product.view.P_sales_view;
import com.ERPMatrix.Application.Model.purchases.Pill.supliserpilldetails;
import com.ERPMatrix.Application.Service.product.product_monitor_service;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillDetailsImpl;
import com.ERPMatrix.Application.Service_implement.finance.pill.pillimpl;
import com.ERPMatrix.Application.Service_implement.finance.pill.reback.reback_pill_details_implment;
import com.ERPMatrix.Application.Service_implement.finance.pill.reback.reback_pill_implment;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpillImpl;
import com.ERPMatrix.Application.Service_implement.purchases.pill.supliserpilldetailsImpl;
import com.ERPMatrix.Application.Service_implement.purchases.reback.rebackPillDetailsImpl;
import com.ERPMatrix.Application.Service_implement.purchases.reback.rebackPillImpl;

@Service
public class product_monitor_implement implements product_monitor_service {

	private supliserpilldetailsImpl purchess_Pill_Details_Impl;
	private supliserpillImpl purchess_Pill_Impl;
	private rebackPillDetailsImpl purchess_Reback_Details_Impl;
	private rebackPillImpl purchess_Reback_Impl;
	private pillDetailsImpl sales_Pill_Details_Impl;
	private pillimpl sales_Pill_Impl;
	private reback_pill_details_implment sales_Reback_Details_Impl;
	private reback_pill_implment sales_Reback_Impl;

	@Autowired
	public product_monitor_implement(supliserpilldetailsImpl purchess_Pill_Details_Impl,
			supliserpillImpl purchess_Pill_Impl, rebackPillDetailsImpl purchess_Reback_Details_Impl,
			rebackPillImpl purchess_Reback_Impl, pillDetailsImpl sales_Pill_Details_Impl, pillimpl sales_Pill_Impl,
			reback_pill_details_implment sales_Reback_Details_Impl, reback_pill_implment sales_Reback_Impl) {
		super();
		this.purchess_Pill_Details_Impl = purchess_Pill_Details_Impl;
		this.purchess_Pill_Impl = purchess_Pill_Impl;
		this.purchess_Reback_Details_Impl = purchess_Reback_Details_Impl;
		this.purchess_Reback_Impl = purchess_Reback_Impl;
		this.sales_Pill_Details_Impl = sales_Pill_Details_Impl;
		this.sales_Pill_Impl = sales_Pill_Impl;
		this.sales_Reback_Details_Impl = sales_Reback_Details_Impl;
		this.sales_Reback_Impl = sales_Reback_Impl;
	}

	@Override
	public List<P_purchess_view> findProductpurchess(String productname) {

		List<P_purchess_view> view = new ArrayList<P_purchess_view>();
		List<supliserpilldetails> pill_details = purchess_Pill_Details_Impl.findByProduct(productname);

		if (pill_details != null) {

			for (int z = 0; z < pill_details.size(); z++) {

			}

		}

		return view;
	}

	@Override
	public List<P_purchess_view> findProductpurchessBysupliser(String productname, String supliser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_purchess_view> findProductpurchessBysuplisernameAndDate(String productname, String supliser,
			String fdate, String sdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_purchess_reback_view> findProductpurchessReback(String productname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_purchess_reback_view> findProductpurchessRebackBysupliser(String productname, String supliser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_purchess_reback_view> findProductpurchessRebackBysuplisernameAndDate(String productname,
			String supliser, String fdate, String sdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_view> findProductSales(String productname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_view> findProductSalesByClientname(String productname, String clientname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_view> findProductSalesByClientnameAndDate(String productname, String clientname, String fdate,
			String sdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_reback_view> findProductSalesReback(String productname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_reback_view> findProductSalesRebackByClientname(String productname, String clientname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<P_sales_reback_view> findProductSalesRebackByClientnameAndDate(String productname, String clientname,
			String fdate, String sdate) {
		// TODO Auto-generated method stub
		return null;
	}

}
