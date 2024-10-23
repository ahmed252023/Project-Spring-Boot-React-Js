package com.project.demo.Service.imple;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.demo.Service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {
	
	public String generateToken(UserDetails userDetais) {
		return Jwts.builder().setSubject(userDetais.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public String generateRefreshToken(Map<String, Object> extractClaims,  UserDetails userDetais) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetais.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000))
				.signWith(getSiginKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}
		
	
	private Key getSiginKey() {
		byte[] key = Decoders.BASE64.decode("FmXVHt3VWx1nv3xvLfKhR9Y3I9FHWjdlH8JyYd6OeTA=");
		return Keys.hmacShaKeyFor(key);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
	}
	
	public boolean isTokenValid(String token, UserDetails userdetails) {
		final String username = extractUserName(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpierd(token));
	}
	
	private Boolean isTokenExpierd(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
	
	
	
	
	

}
