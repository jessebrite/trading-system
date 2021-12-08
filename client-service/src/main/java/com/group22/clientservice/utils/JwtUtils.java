package com.group22.clientservice.utils;

import com.group22.clientservice.service.impl.UserDetailsImplementation;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${abj.app.jwtSecret}")
	private String jwtSecret;

	@Value("${abj.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImplementation userPrincipal =
			(UserDetailsImplementation) authentication.getPrincipal();
		return Jwts.builder()
			.setSubject(userPrincipal.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException exception) {
			logger.error("Invalid JWT signature: {}", exception.getMessage());
		}
		return "Error found while parsing jwt";
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException exception) {
			logger.error("Invalid JWT signature: {}", exception.getMessage());
		} catch (MalformedJwtException exception) {
			logger.error("Invalid JWT token: {}", exception.getMessage());
		} catch (ExpiredJwtException exception) {
			logger.error("JWT token is expired: {}", exception.getMessage());
		} catch (UnsupportedJwtException exception) {
			logger.error("JWT token is unsupported: {}", exception.getMessage());
		} catch (IllegalArgumentException exception) {
			logger.error("JWT claims string is empty: {}", exception.getMessage());
		}
		return false;
	}
}

