package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.entity.User1;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpel implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto createcomment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);	
		Comment savedcomment = this.commentRepo.save(comment);
		return this.modelmapper.map(savedcomment, CommentDto.class);
	}

	@Override
	public void deletecomment(Integer CommentId) {
		Comment comment = this.commentRepo.findById(CommentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", CommentId));
		this.commentRepo.delete(comment);	

	}

}
