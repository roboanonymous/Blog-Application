package com.blog.entity;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer postId;
	
	@Column(name = "Post_title" , nullable = false, length = 100)
	 private String title;
	 private String Content;
	 private String imageName;
	 private Date addeddate;
	 
	 @ManyToOne
	 private Category category;
	 
	 @ManyToOne
	 private User1 user1;
	 
	 @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
	 private Set<Comment> comments = new HashSet<>();
	 
	 
}
