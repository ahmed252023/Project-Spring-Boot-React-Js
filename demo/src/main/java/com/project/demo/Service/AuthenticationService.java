package com.project.demo.Service;

import com.project.demo.Dto.JwtAuthenticationResponse;
import com.project.demo.Dto.RefreshTokenRequest;
import com.project.demo.Dto.SignInRequest;
import com.project.demo.Dto.SignUpRequest;
import com.project.demo.entity.User;

public interface AuthenticationService {
	
	User signup(SignUpRequest signUpRequest);
	
	JwtAuthenticationResponse signin(SignInRequest signInRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
