package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Post;
import com.blog.payloads.ApiResponse;
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
	
	// Get By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostbyuser(@PathVariable Integer userId)
	{
		
		List<PostDto> posts = this.postService.getpostbyuser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// Get By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getpostbycategory(@PathVariable Integer categoryId)
	{
			
		List<PostDto> posts = this.postService.getpostbycategories(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// Get all Post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getallpost()
	{
		List<PostDto> posts = this.postService.getAllpost();
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// Get Single Post by Id
	@GetMapping("/posts/{PostId}")
	public ResponseEntity<PostDto> getpostbyId(@PathVariable Integer PostId)
	{
		PostDto posts = this.postService.getpostbyId(PostId);
		return new ResponseEntity<PostDto>(posts, HttpStatus.OK);
	}
	
	// Delete Post
	@DeleteMapping("/posts/{PostId}")
	public ResponseEntity<ApiResponse> deletepost (@PathVariable Integer PostId)
	{
		this.postService.deletepost(PostId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is Successfully Deleted !!" , true) , HttpStatus.OK);
	}
	
	// Update Post
	@PutMapping("/posts/{PostId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto, @PathVariable Integer PostId)
	{
		PostDto updatepost = this.postService.updatepost(postDto, PostId);
		return new ResponseEntity<PostDto>(updatepost , HttpStatus.OK);
	}
	

}
