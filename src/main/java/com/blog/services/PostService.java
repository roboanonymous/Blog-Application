package com.blog.services;

import java.util.List;

import com.blog.entity.Post;
import com.blog.payloads.PostDto;

public interface PostService {

	//create
	Post createpost(PostDto postDto);
	
	//update
	Post updatepost (PostDto postDto , Integer postId);
	
	// delete
	void deletepost (Integer postId);
	
	// Get all posts
	List<Post> getAllpost();
	
	//Get Single Post
	Post getpostbyId (Integer postId);
	
	//Get post by categories
	List<Post> getpostbycategories(Integer CategoryId);
	
	//Get post by User
	List<Post> getpostbyuser(Integer UserId);
	
	//Search Post
	List<Post> searchPost(String Keyword);
	
}
