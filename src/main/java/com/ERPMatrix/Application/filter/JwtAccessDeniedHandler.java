package com.ERPMatrix.Application.filter;

import static com.ERPMatrix.Application.Constant.SecurityConfig.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.ERPMatrix.Application.Constant.HttpResponse;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws ServletException, JsonGenerationException, JsonMappingException, IOException {

		HttpResponse httpresponse = new HttpResponse(UNAUTHORIZED.value(), UNAUTHORIZED,
				UNAUTHORIZED.getReasonPhrase().toUpperCase(), ACCESS_DENIED_MESSAGE);

		
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(UNAUTHORIZED.value());
		OutputStream outputstream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputstream, httpresponse);
		outputstream.flush();

	}

}
