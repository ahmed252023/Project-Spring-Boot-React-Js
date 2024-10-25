package com.project.demo.Security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity

//public class SecurityConfig {
	
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf().disable()
//	            .authorizeRequests()
//	                .requestMatchers("/api/cars/**").authenticated() // secure API endpoints
//	                .anyRequest().permitAll() // allow all other requests
//	            .and()
//	            .httpBasic(); // for testing purposes, use basic auth (can be replaced by JWT)
//	        return http.build();
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder(); // for encoding passwords
//	    }
//	}