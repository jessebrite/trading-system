package com.group22.clientservice.utils;

import com.group22.clientservice.model.Client;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${abj.app.jwtSecret}")
    private String jwtSecret;

    @Value("${abj.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Client client) {

        Map<String, Object> claims = new HashMap<>() {{
            put("firstname", client.getFirstName());
            put("lastname", client.getLastName());
            put("role", client.getRole().name());
        }};

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuer("Trading")
                .setId(client.getId().toString())
                .setSubject(client.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret);
        return jwtBuilder.compact();
    }

    public String getUserNameFromJwtToken(String token) {
        System.out.println(token);
//        try {

        var s = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        System.out.println(s);
        return s.toString();
//        } catch (SignatureException exception) {
//            logger.error("Invalid JWT signature: {}", exception.getMessage());
//        }
//        return "Error found while parsing jwt";
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

