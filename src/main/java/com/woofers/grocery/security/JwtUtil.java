package com.woofers.grocery.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

	 private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    public String generateToken(String username) {
	        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
	                .signWith(KEY).compact();
	    }

	    public String extractUsername(String token) {
	        return Jwts.parserBuilder().setSigningKey(KEY).build()
	                .parseClaimsJws(token)
	                .getBody().getSubject();
	    }
}
