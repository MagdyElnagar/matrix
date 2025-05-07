package com.ERPMatrix.Application.enumeration;

import static com.ERPMatrix.Application.Constant.Authority.ROLE_ACCOUNTAT_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.ROLE_ACCOUNTAT_MANAGER_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.ROLE_ADMIN_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.ROLE_SALE_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.ROLE_STORE_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.ROLE_SUPER_ADMIN_AUTHORITIES;
import static com.ERPMatrix.Application.Constant.Authority.USER_ROLE_AUTHORITIES;

public enum Role {

	/*
	 *
	 * ROLE_USER(USER_AUTHORITIES), ROLE_HR(HR_AUTHORITIES),
	 * ROLE_MANAGER(MANAGER_AUTHORITIES), ROLE_ADMIN_(ADMIN_AUTHORITIES),
	 * ROLE_SUPER_ADMIN(SUPER_USER_AUTHORITIES), ROLE_SALES(ROLE_SALE),
	 * ROLE_ACCOUNTAT(ACCOUNTAT), ROLE_TELESALES(TELESALES), OLE_STORE(STORE);
	 */

	ROLE_ACCOUNTAT(ROLE_ACCOUNTAT_AUTHORITIES), ROLE_ACCOUNTAT_MANAGER(ROLE_ACCOUNTAT_MANAGER_AUTHORITIES),
	ROLE_ADMIN(ROLE_ADMIN_AUTHORITIES), ROLE_SALE(ROLE_SALE_AUTHORITIES), ROLE_STORE(ROLE_STORE_AUTHORITIES),
	ROLE_SUPER_ADMIN(ROLE_SUPER_ADMIN_AUTHORITIES), ROLE_USER(USER_ROLE_AUTHORITIES);

	private String[] authorities;

	Role(String... authorities) {

		this.authorities = authorities;
	}

	public String[] getAuthorities() {
		return authorities;
	}

}
