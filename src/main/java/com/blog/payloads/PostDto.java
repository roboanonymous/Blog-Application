package com.blog.payloads;

import java.sql.Date;

import com.blog.entity.Category;
import com.blog.entity.User1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	 private String title;
	 private String Content;
	 private String imageName;
	 private Date addeddate;	  
	 private CategoryDto category; 	
	 private UserDto user1;
}
