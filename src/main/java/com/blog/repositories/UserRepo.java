package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.User1;

public interface UserRepo extends JpaRepository<User1, Integer> {

}
