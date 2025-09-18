package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.config.JwtUtil;
import com.project.dto.request.LoginRequest;
import com.project.dto.response.LoginResponse;
import com.project.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginForm){
		System.out.println("Entry: "+loginForm.getEmail()+" and "+loginForm.getPassword());
		try {			
			Authentication authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginForm.getEmail(),
					loginForm.getPassword()
			));

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginForm.getEmail());
			String jwt = jwtUtil.generateToken(userDetails);
			
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Authorization", jwt);
			
//			return new ResponseEntity("Welcome to Home: "+loginForm.getEmail(), headers, HttpStatusCode.valueOf(200));
			return ResponseEntity.status(HttpStatus.OK).body(jwt);
		}
		catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
}
