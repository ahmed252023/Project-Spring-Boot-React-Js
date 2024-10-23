package com.project.demo.Dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	
	private String refreshToken;
	
}
