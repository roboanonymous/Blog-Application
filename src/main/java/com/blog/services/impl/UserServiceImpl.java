package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User1;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
import com.blog.exception.*;

@Service
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
	public UserDto updateUser(UserDto userDto, Integer userID) {
		// TODO Auto-generated method stub
		User1 user1 = userrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User1", "id" , userID));
		
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setAbout(userDto.getAbout());
		user1.setPassword(userDto.getPassword());
		
		User1 updatedUser = userrepo.save(user1);
		UserDto userDto2 = this.userTodto(updatedUser);
		
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userID) {
		// TODO Auto-generated method stub
		User1 user1 = userrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User1", "id" , userID));
		
		return this.userTodto(user1);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User1> users =this.userrepo.findAll();
		List<UserDto> userdtos = users.stream().map(user1 -> this.userTodto(user1)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userID) {
		// TODO Auto-generated method stub
		User1 user1 = userrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User1", "id" , userID));
		this.userrepo.delete(user1);
	

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
