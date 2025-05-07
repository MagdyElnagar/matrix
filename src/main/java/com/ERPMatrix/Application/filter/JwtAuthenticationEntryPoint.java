package com.ERPMatrix.Application.filter;

import static com.ERPMatrix.Application.Constant.SecurityConfig.FORBIDDEN_MESSAGE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.ERPMatrix.Application.Constant.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException {
		HttpResponse httpresponse = new HttpResponse(FORBIDDEN.value(), FORBIDDEN,
				FORBIDDEN.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(FORBIDDEN.value());
		OutputStream outputstream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputstream, httpresponse);
		outputstream.flush();
	}

}
