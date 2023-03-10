package com.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.JwtAuthResponse;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken()
	{
		
	}
}
