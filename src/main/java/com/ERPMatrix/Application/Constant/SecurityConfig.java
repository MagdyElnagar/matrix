package com.ERPMatrix.Application.Constant;

public class SecurityConfig {
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String AUTHORITIES = "authorities";
	public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String GET_ARRAYS_ADMINISTRATION = "Medical Matrix";
	public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String OPTION_HTTP_METHOD = "OPTIONS";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String[] PUBLIC_METHODS = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };

	public static final String[] PUBLIC_URLS = { "/css/**", "/js/**", "/images/**" ,"/JasperReport/**", "/user/PC_INFO/**", "/user/token/**",
			"/user/login","/login","/logining", "/user/register", "/user/resetpassword/**", "/fileStorage/**",

			"contactus/**", "/user/img/**" };

	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";

	public static final String TOKEN_PREFIX = "Bearer ";

}
