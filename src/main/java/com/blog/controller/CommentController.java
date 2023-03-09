package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Comment;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createcomment(@RequestBody CommentDto comment , @PathVariable Integer postId)
	{
		CommentDto com = this.commentService.createcomment(comment, postId);
		return new ResponseEntity<CommentDto>(com , HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deletecomment( @PathVariable Integer commentId)
	{
		this.commentService.deletecomment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is Successfully Deleted !!" , true) , HttpStatus.OK);
	}
	
}
