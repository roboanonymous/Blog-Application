package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Post;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// Create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId)
	{
		PostDto createpost = this.postService.createpost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createpost , HttpStatus.CREATED);
	}

}
