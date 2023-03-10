package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.blog.security.CustomUserDetailService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService CustomUserDetailsService;
	
	@Autowired
	private JWTAuthenticationEntryPont jwtAuthenticationEntryPoint
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider DaoAuthenticationProvider = new DaoAuthenticationProvider();
		DaoAuthenticationProvider.setUserDetailsService(this.CustomUserDetailsService());
		DaoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return DaoAuthenticationProvider;
	}
	
	
	// Configure Method ... 
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers(PUBLIC_URLS)
		.permitAll()
		.requestMatchers(HttpMethod.GET)
		.permitAll()
		.authenticated
		.and().exceptionHandling()
		.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthentication);
		
		http.authenticationProvider(authenticationProvider());
		
		DefaultSecurityFilterChain build = http.build();
		
		return build;
	}
	
	
	
}
