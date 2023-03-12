package com.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.User1;

public interface UserRepo extends JpaRepository<User1, Integer> {

	Optional<User1> findbyOptional(String name);
	
}
