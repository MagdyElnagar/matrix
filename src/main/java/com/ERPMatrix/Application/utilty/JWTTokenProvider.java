package com.ERPMatrix.Application.utilty;

import static com.ERPMatrix.Application.Constant.SecurityConfig.AUTHORITIES;
import static com.ERPMatrix.Application.Constant.SecurityConfig.EXPIRATION_TIME;
import static com.ERPMatrix.Application.Constant.SecurityConfig.GET_ARRAYS_ADMINISTRATION;
import static com.ERPMatrix.Application.Constant.SecurityConfig.GET_ARRAYS_LLC;
import static com.ERPMatrix.Application.Constant.SecurityConfig.TOKEN_CANNOT_BE_VERIFIED;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.util.Arrays.stream;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.ERPMatrix.Application.Model.User.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;



@Component
@PropertySource(value = {"classpath:application.proprites"})

public class JWTTokenProvider {

	@Value("${jwt.secret}")
	private String secret;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

	public String generateJwtToken(UserPrincipal userprincipal) {

		String[] claims = getClaimsFromUser(userprincipal);

		
		
		return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION).withIssuedAt(new Date())
				.withSubject(userprincipal.getUsername()).withArrayClaim(AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(secret.getBytes()));

	}

	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userPasswordAuthToken = new UsernamePasswordAuthenticationToken(username,
				null, authorities);
		userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		return userPasswordAuthToken;

	}

	public List<GrantedAuthority> getAuthorities(String token) {
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private String[] getClaimsFromToken(String token) {

		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
	}

	private String[] getClaimsFromUser(UserPrincipal userprincipal) {
		List<String> authorities = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : userprincipal.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
	}

	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = HMAC512(secret);
			verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
		}
		return verifier;
	}

	public String getSubject(String token) {

		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getSubject();
	}

	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}

	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJWTVerifier();
		return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);

	}

}
