package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice; 
	
	// Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createuserdto =  this.userservice.createUser(userDto);
		return new ResponseEntity<>(createuserdto , HttpStatus.CREATED);
	}
	
	// Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable("userId") Integer userId)
	{
		UserDto updateduser =  this.userservice.updateUser(userDto, userId);
		return ResponseEntity.ok(updateduser);
	}
	
	// Delete Single User
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId)
	{
		this.userservice.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully" , true) , HttpStatus.OK);
		
	}
	
	// Get All User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getallUser()
	{
		
		return ResponseEntity.ok(this.userservice.getAllUsers());
	}
	
	// Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId)
	{
		
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}
	
	
	
	
	
	
}
