package com.blog.services;

import java.util.List;

import com.blog.entity.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createpost(PostDto postDto , Integer userId, Integer CategoryId);
	
	//update
	PostDto updatepost (PostDto postDto , Integer postId);
	
	// delete
	void deletepost (Integer postId);
	
	// Get all posts
	PostResponse getAllpost(Integer pageNumeber , Integer pagesize , String sortby);
	
	//Get Single Post
	PostDto getpostbyId (Integer postId);
	
	//Get post by categories
	List<PostDto> getpostbycategories(Integer CategoryId);
	
	//Get post by User
	List<PostDto> getpostbyuser(Integer UserId);
	
	//Search Post
	List<Post> searchPost(String Keyword);
	
}
