package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entity.User1;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userrepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User1 user1 = this.dtoToUSer(userDto);
		User1 savedUser = this.userrepo.save(user1);
		
		return this.userTodto(savedUser);

	}

	@Override
	public UserDto updateUser(UserDto user1, Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userID) {
		// TODO Auto-generated method stub

	}
	
	private User1 dtoToUSer(UserDto userDto)
	{
		User1 user1 = new User1();
		user1.setId(userDto.getId());
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setAbout(userDto.getAbout());
		user1.setPassword(userDto.getPassword());
		
		return user1;
	}
	
	private UserDto userTodto(User1 user1)
	{
		UserDto userDto = new UserDto();
		
		userDto.setId(user1.getId());
		userDto.setName(user1.getName());
		userDto.setEmail(user1.getEmail());
		userDto.setAbout(user1.getAbout());
		userDto.setPassword(user1.getPassword());
		
		return userDto;
	}

}
