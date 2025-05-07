package com.ERPMatrix.Application.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.ERPMatrix.Application.Model.User.UserPrincipal;
import com.ERPMatrix.Application.Service.loginAtempServices;

@Component
public class AuthenticationSuccessListener {

	private loginAtempServices loginAtempServices;

	@Autowired
	public AuthenticationSuccessListener(loginAtempServices loginAtempServices) {
		super();
		this.loginAtempServices = loginAtempServices;
	}
	
	@EventListener
	public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
		Object princibal = event.getAuthentication().getPrincipal();
		if (princibal instanceof UserPrincipal) {
			UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
			
			loginAtempServices.evictUserFromLoginAttemptCache(user.getUsername());
		}

	}


}
