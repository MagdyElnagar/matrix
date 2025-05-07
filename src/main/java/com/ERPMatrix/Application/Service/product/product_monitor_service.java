package com.ERPMatrix.Application.Service.product;

import java.util.List;

import com.ERPMatrix.Application.Model.product.view.P_purchess_reback_view;
import com.ERPMatrix.Application.Model.product.view.P_purchess_view;
import com.ERPMatrix.Application.Model.product.view.P_sales_reback_view;
import com.ERPMatrix.Application.Model.product.view.P_sales_view;

public interface product_monitor_service {

	List<P_purchess_view> findProductpurchess(String productname);

	List<P_purchess_view> findProductpurchessBysupliser(String productname, String supliser);

	List<P_purchess_view> findProductpurchessBysuplisernameAndDate(String productname, String supliser, String fdate,
			String sdate);

	List<P_purchess_reback_view> findProductpurchessReback(String productname);

	List<P_purchess_reback_view> findProductpurchessRebackBysupliser(String productname, String supliser);

	List<P_purchess_reback_view> findProductpurchessRebackBysuplisernameAndDate(String productname, String supliser,
			String fdate, String sdate);

	List<P_sales_view> findProductSales(String productname);

	List<P_sales_view> findProductSalesByClientname(String productname, String clientname);

	List<P_sales_view> findProductSalesByClientnameAndDate(String productname, String clientname, String fdate,
			String sdate);

	List<P_sales_reback_view> findProductSalesReback(String productname);

	List<P_sales_reback_view> findProductSalesRebackByClientname(String productname, String clientname);

	List<P_sales_reback_view> findProductSalesRebackByClientnameAndDate(String productname, String clientname,
			String fdate, String sdate);

}
