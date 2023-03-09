package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User1;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser1(User1 user1);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchbytitle(@Param("key") String title);
}
