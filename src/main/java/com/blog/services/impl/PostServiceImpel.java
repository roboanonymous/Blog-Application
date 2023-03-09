package com.blog.services.impl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User1;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpel implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo; 
	

	
	@Override
	public PostDto createpost(PostDto postDto,Integer userId, Integer CategoryId) {
		// TODO Auto-generated method stub
		User1 user1 = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		
		Category category = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));
		
		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());
		post.setUser1(user1);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		 
		return this.modelmapper.map(newPost, PostDto.class);
		
	}

	@Override
	public PostDto updatepost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedpost = this.postRepo.save(post);
		
		return this.modelmapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletepost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllpost(Integer pageNumeber , Integer pagesize, String sortby) {
		
		
		org.springframework.data.domain.Pageable p  = PageRequest.of(pageNumeber, pagesize, org.springframework.data.domain.Sort.by(sortby));
		
		Page<Post> pagepost = this.postRepo.findAll(p);
		List<Post> allPost = pagepost.getContent();
		List<PostDto> dtos = allPost.stream().map((post) -> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());		
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPagesize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalpages(pagepost.getTotalPages());
		postResponse.setLastpage(pagepost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getpostbyId(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getpostbycategories(Integer CategoryId) {
		
		Category cat = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", CategoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> dtos = posts.stream().map((post) -> this.modelmapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public List<PostDto> getpostbyuser(Integer UserId) {
		
		User1 user1 = this.userRepo.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", UserId));
		List<Post> posts = this.postRepo.findByUser1(user1);
		List<PostDto> dtos = posts.stream().map((post) -> this.modelmapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		
		return dtos;
	}

	@Override
	public List<PostDto> searchPost(String Keyword) {
		
		List<Post> posts = this.postRepo.searchbytitle("%"+Keyword+"%");
        List<PostDto> dtos = posts.stream().map((post) -> this.modelmapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

}
