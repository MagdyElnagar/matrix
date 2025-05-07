package com.ERPMatrix.Application.exception.domain;

public class AccountExpiredException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4567875259338215488L;

	public AccountExpiredException(String message) {
        super(message);
    }

}
