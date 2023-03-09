package com.blog.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entity.Category;
import com.blog.entity.Comment;
import com.blog.entity.User1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	 private Integer postId;
	 private String title;
	 private String Content;
	 private String imageName;
	 private Date addeddate;	  
	 private CategoryDto category; 	
	 private UserDto user1;
	 
	 private Set<CommentDto> comments = new HashSet<>();
}
