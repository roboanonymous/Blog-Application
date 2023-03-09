package com.blog.repositories;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
