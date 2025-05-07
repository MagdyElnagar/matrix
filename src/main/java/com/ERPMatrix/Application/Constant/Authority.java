package com.ERPMatrix.Application.Constant;

public class Authority {

	public static final String[] ROLE_ACCOUNTAT_AUTHORITIES = { "ROLE_SALES", "ROLE_ACCOUNTAT", "store" };
	public static final String[] ROLE_ACCOUNTAT_MANAGER_AUTHORITIES = { "ROLE_SALE_ORDER", "ROLE_SALE_PILL",
			"ROLE_SEARCH_SALE_ORDER", "ROLE_SEARCH_SALE_PILL", "ROLE_SALE_REBACK", "ROLE_SUPLISER_MOVE" };
	public static final String[] ROLE_ADMIN_AUTHORITIES = { "ROLE_SALES", "ROLE_ACCOUNTAT", "sale", "store", "admin" };
	public static final String[] ROLE_ROLE_SALES_ADMIN_AUTHORITIES = { "user:read", "user:update", "user:creat",
			"user:delete" };
	public static final String[] ROLE_ROLE_SALES_AUTHORITIES = { "ROLE_SALES", "ROLE_ACCOUNTAT", "store" };
	public static final String[] ROLE_SALE_AUTHORITIES = { "sale", "store" };
	public static final String[] ROLE_STORE_AUTHORITIES = { "store" };
	public static final String[] ROLE_SUPER_ADMIN_AUTHORITIES = { "ROLE_SALES", "ROLE_ACCOUNTAT", "sale", "store",
			"admin", "super_admin", "accoutat_admin" };
	public static final String[] USER_ROLE_AUTHORITIES = { "ROLE_HOME" };
}