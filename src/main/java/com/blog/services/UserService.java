package com.blog.services;


import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user1);
	UserDto updateUser(UserDto user1 , Integer userID);
	UserDto getUserById(Integer userID);
	List <UserDto> getAllUsers();
	void deleteUser(Integer userID);
	
}
