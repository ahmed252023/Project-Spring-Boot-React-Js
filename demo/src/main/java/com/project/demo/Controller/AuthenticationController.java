package com.project.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.Dto.JwtAuthenticationResponse;
import com.project.demo.Dto.RefreshTokenRequest;
import com.project.demo.Dto.SignInRequest;
import com.project.demo.Dto.SignUpRequest;
import com.project.demo.Service.AuthenticationService;
import com.project.demo.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {
   private final AuthenticationService authenticationService;
   
   
   @PostMapping("/signup")
   public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
	   return ResponseEntity.ok(authenticationService.signup(signUpRequest));
   }
   
   @PostMapping("/signin")
   public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
	   return ResponseEntity.ok(authenticationService.signin(signInRequest));
   }
   
   @PostMapping("/refresh")
   public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
	   return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
   }
   
   
   
   
   
}
