/*
 *
 * All copy right for magdy elnagar
 * this is not an open source code
 * dont try to play here or change any thing
 * i hade devolpemnt this app if any one wanna change any thing at this app source code will destroy
 * for contact me phone: 01119811130 / 01093222432  Email: megoelnagar18@gmail.com whatsapp : 01119811130
 * After finish this app i have to do
 * tax must work
 * bounce must work
 * Java runtime version: 8
 * tomcate version :8.5.4
 * 
 * */

package com.ERPMatrix;

import java.util.Arrays;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
public class ErpMatrixApplication extends SpringBootServletInitializer   {

	public static void main(String[] args) {
		System.out.println("App Work");
		SpringApplication.run(ErpMatrixApplication.class, args);

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("Origin", "Content-Type , Accept", "Accept", "Jwt-Token",
				"Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Credentials"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;

	}
	/*
	 * UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
	 * CorsConfiguration(); config.setAllowCredentials(true);
	 * config.setAllowedOrigins(Collections.singletonList("*"));
	 * config.setAllowedMethods( Arrays.asList("Access-Control-Allow-Methods",
	 * "GET", "POST", "PUT", "DELETE", "OPTIONS"));
	 * config.setAllowedHeaders(Arrays.asList("Origin",
	 * "Access-Control-Request-Headers", "Content-Type", "Accept", "Jwt-Token",
	 * "Authorization", "Origin , Accept", "X-Request-With",
	 * "Access-Control-Request-Method", "Access-Control-Allow-Headers"));
	 * config.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept",
	 * "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
	 * "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	 * urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);
	 * return new CorsFilter(urlBasedCorsConfigurationSource);
	 */;
}
