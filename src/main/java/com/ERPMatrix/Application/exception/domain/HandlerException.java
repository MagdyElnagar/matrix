package com.ERPMatrix.Application.exception.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public HandlerException(String message, String className) {

		super(message);
		LOGGER.warn(">>>>>> Error Class  >>>>>>> : " + className);

	}

}
