package com.codewithkrish.blog.repositeres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithkrish.blog.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> 
{
	
	

}
