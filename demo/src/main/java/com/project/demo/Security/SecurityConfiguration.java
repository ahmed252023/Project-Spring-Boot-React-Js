package com.project.demo.Security;

import org.apache.catalina.Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.demo.Service.UserService;
import com.project.demo.entity.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final UserService userService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/api/v1/auth/**").permitAll() // Allow all requests for the auth path
	            .requestMatchers("/api/v1/admin").hasAuthority("ADMIN") // Allow only ADMIN role for admin path
	            .requestMatchers("/api/v1/user").hasAuthority("USER")   // Allow only USER role for user path
	            .anyRequest().authenticated() // Any other request must be authenticated
	        )
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions
	        )
	        .authenticationProvider(authenticationProvider()) // Set your custom authentication provider
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter

	    return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
	throws Exception {
		return config.getAuthenticationManager();
	
	}
	

}
