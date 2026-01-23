package com.woofers.grocery.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.woofers.grocery.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

	 private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	 public String generateToken(User user) {

		    Map<String, Object> claims = new HashMap<>();
		    claims.put("role", user.getRole().name());

		    return Jwts.builder()
		            .setClaims(claims)
		            .setSubject(user.getUsername())
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		            .signWith(KEY, SignatureAlgorithm.HS256)
		            .compact();
		}
	    public String extractUsername(String token) {
	        return Jwts.parserBuilder().setSigningKey(KEY).build()
	                .parseClaimsJws(token)
	                .getBody().getSubject();
	    }
}
