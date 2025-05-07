package com.ERPMatrix.Application.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ERPMatrix.Application.Constant.HttpResponse;
import com.ERPMatrix.Application.exception.domain.EmailExistException;
import com.ERPMatrix.Application.exception.domain.EmailNotFoundException;
import com.ERPMatrix.Application.exception.domain.HandlerException;
import com.ERPMatrix.Application.exception.domain.PasswordExistException;
import com.ERPMatrix.Application.exception.domain.PasswordNotFoundException;
import com.ERPMatrix.Application.exception.domain.UserNotFoundException;
import com.ERPMatrix.Application.exception.domain.UsernameExistException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
	public static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
	public static final String ACCOUNT_Expired = "Your account has been Expired. Please contact administration or ERPMatrix Support";
	public static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
	public static final String ERROR_PATH = "/error";
	public static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
	public static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
	public static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request contact administration or MedicalMatrix Support ";
	public static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
	public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HttpResponse> accessDeniedException() {
		return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<HttpResponse> accountDisabledException() {
		return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
	}

	@ExceptionHandler(AccountExpiredException.class)
	public ResponseEntity<HttpResponse> accountExpiredException() {
		return createHttpResponse(FORBIDDEN, ACCOUNT_Expired);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HttpResponse> badCredentialsException() {
		return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
	}

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}

	@ExceptionHandler(EmailExistException.class)
	public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@ExceptionHandler(HandlerException.class)
	public ResponseEntity<HttpResponse> HandlerException(HandlerException exception) {

		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<HttpResponse> iOException(IOException exception) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
	}

	@ExceptionHandler(LockedException.class)
	public ResponseEntity<HttpResponse> lockedException() {
		return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
		return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
		return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
	}

	@GetMapping(ERROR_PATH)
	public ResponseEntity<HttpResponse> notFound404() {

		return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
	}
	
	
	/*
	 * @Override public String getErrorPath() { return ERROR_PATH; }
	 */

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(PasswordExistException.class)
	public ResponseEntity<HttpResponse> PasswordExistException(PasswordExistException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(PasswordNotFoundException.class)
	public ResponseEntity<HttpResponse> PasswordNotFoundException(PasswordNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
		return createHttpResponse(UNAUTHORIZED, exception.getMessage());
	}

	@ExceptionHandler(UsernameExistException.class)
	public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

}
