package com.ERPMatrix.Application.listener;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import com.ERPMatrix.Application.Service.loginAtempServices;


@Component
public class AuthenticationFailureListener {

	private loginAtempServices loginAtempServices;

	
	@Autowired
	public AuthenticationFailureListener(loginAtempServices loginAtempServices) {
	
		this.loginAtempServices = loginAtempServices;
	}
	
	
	
	@EventListener
	public void onAuthentcationFailure(AuthenticationFailureBadCredentialsEvent event) throws ExecutionException {
		
		Object principal =  event.getAuthentication().getPrincipal();
		
		if (principal instanceof String) {
			String username = (String)event.getAuthentication().getPrincipal();
			loginAtempServices.addUserToLoginAttemptCache(username);
		}
		 
		
	}
	
}
