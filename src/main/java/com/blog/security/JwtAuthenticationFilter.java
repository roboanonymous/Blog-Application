package com.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwttokenHelper jwttokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get Token
		String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);
		
		String username = null;
		String token = null;
		
		if(request != null && requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7);
			username= this.jwttokenHelper.getUsernameFromToken(token);
			try {
				username= this.jwttokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
			}catch (ExpiredJwtException e) {
				System.out.println("JWT token has expired");
			}catch (MalformedJwtException e) {
				System.out.println("Invalid JWT");
			}
		}
		else
		{
			System.out.println("JWT token does not begin with Bearer");
		}
		
		
		// Once We get the token , now validate
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwttokenHelper.validToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			else
			{
				System.out.println("Invalid JWT token");
			}
		}
		else
		{
			System.out.println("Username is null or  context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
