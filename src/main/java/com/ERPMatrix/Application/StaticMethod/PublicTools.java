package com.ERPMatrix.Application.StaticMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicTools {
	
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    
    public void sendLogMessage(String message) {
    	
    	
    	LOGGER.info(message);
    	
    	
    }

	
	

}
