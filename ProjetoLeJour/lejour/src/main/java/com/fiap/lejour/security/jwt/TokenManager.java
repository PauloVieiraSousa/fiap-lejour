package com.fiap.lejour.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fiap.lejour.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	@Value("${songsfy.jwt.secretkey}")
	private String secretKey;
	
	@Value("${songsfy.jwt.expiration}")
	private long expirationMillis;
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		final Date now = new Date();
		final Date expiration = new Date(now.getTime() + this.expirationMillis);
		
		return Jwts.builder()
				.setIssuer("Lejour Forum Api")
				.setSubject(Long.toString(user.getId()))
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
		
	}
	
	
	public boolean isValid(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwt);
			return true;
		}catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	
	public Long getUserIdFromToken(String jwt) {
		Claims claims = Jwts.parser()
				.setSigningKey(this.secretKey)
				.parseClaimsJws(jwt)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}
}
