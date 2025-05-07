package com.ERPMatrix.Application.Config;

import static com.ERPMatrix.Application.Constant.SecurityConfig.PUBLIC_URLS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.ERPMatrix.Application.Constant.Authority;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ERPMatrix.Application.filter.JwtAccessDeniedHandler;
import com.ERPMatrix.Application.filter.JwtAuthenticationEntryPoint;
import com.ERPMatrix.Application.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigration extends WebSecurityConfigurerAdapter {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JwtAccessDeniedHandler jwtaccessDeniedHandler;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtAuthorizationFilter jwtauthorizationFilter;
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	private UserDetailsService userdetailsService;

	@Autowired
	public SecurityConfigration(JwtAuthorizationFilter jwtauthorizationFilter,
			JwtAccessDeniedHandler jwtaccessDeniedHandler, JwtAuthenticationEntryPoint jwtauthenticationEntryPoint,
			@Qualifier("userDetailsService") UserDetailsService userdetailsService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.jwtauthorizationFilter = jwtauthorizationFilter;
		this.jwtaccessDeniedHandler = jwtaccessDeniedHandler;
		this.jwtAuthenticationEntryPoint = jwtauthenticationEntryPoint;
		this.userdetailsService = userdetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(STATELESS).and()
				.authorizeRequests().antMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated().and()
				.exceptionHandling().accessDeniedHandler(jwtaccessDeniedHandler)
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				;*/
		
		  http.cors().and()
          .csrf().disable().authorizeRequests()
          .antMatchers(HttpMethod.GET, "/home").hasRole("USER")
          .antMatchers(HttpMethod.GET, "/product").hasRole("USER")
          .antMatchers(PUBLIC_URLS).permitAll()
          .anyRequest().authenticated()
          .and()
          .addFilterBefore(jwtauthorizationFilter, UsernamePasswordAuthenticationFilter.class)
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
