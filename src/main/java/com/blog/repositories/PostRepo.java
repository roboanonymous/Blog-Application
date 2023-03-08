package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User1;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser1(User1 user1);
	
	List<Post> findByCategory(Category category);
	
}
