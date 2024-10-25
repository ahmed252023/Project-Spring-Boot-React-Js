package com.project.demo.Service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
	
	String extractUserName(String token);
	
	String generateToken(UserDetails userDetailes);
	
	boolean isTokenValid(String token, UserDetails userDetails);

	String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails);
}
