package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.JwtAuthRequest;
import com.blog.payloads.JwtAuthResponse;
import com.blog.security.JwttokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwttokenHelper helper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken( @RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername() , request.getPassword());
		
		UserDetails details = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.helper.generateToken(details);
		
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(authResponse, HttpStatus.OK);
	}
	
	private void authenticate(String username , String password) throws Exception
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);			
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			
			System.out.println("Bad Credentials");
			throw new Exception("Invalid username and password !!");
		}
		
	}
	
	
}
