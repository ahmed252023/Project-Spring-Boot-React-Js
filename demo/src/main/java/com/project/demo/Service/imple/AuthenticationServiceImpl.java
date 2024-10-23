package com.project.demo.Service.imple;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.demo.Dto.JwtAuthenticationResponse;
import com.project.demo.Dto.RefreshTokenRequest;
import com.project.demo.Dto.SignInRequest;
import com.project.demo.Dto.SignUpRequest;
import com.project.demo.Repository.UserRepo;
import com.project.demo.Service.AuthenticationService;
import com.project.demo.Service.JWTService;
import com.project.demo.entity.Role;
import com.project.demo.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.var;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	private final UserRepo userRepository ;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager ;
	private final JWTService jwtService;
	
	public User signup(SignUpRequest signUprequest) {
		User user = new User();
		
		user.setEmail(signUprequest.getEmail());
		user.setFirstname(signUprequest.getFirstname());
		user.setSecondname(signUprequest.getLastname());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUprequest.getPassworld()));
		
		return userRepository.save(user);
	}
	
	public JwtAuthenticationResponse signin(SignInRequest signinRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), 
				signinRequest.getPassworld()));
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		return jwtAuthenticationResponse;
		
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
		
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
		return jwtAuthenticationResponse;
		}
		return null;
	}
	
}
