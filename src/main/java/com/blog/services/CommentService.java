package com.blog.services;


import com.blog.payloads.CommentDto;


public interface CommentService {

	CommentDto createcomment(CommentDto commentDto , Integer postId);
	
	void deletecomment(Integer CommentId);
}
