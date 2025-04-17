package com.codewithkrish.blog.payloads;

import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentDto 
{
private int id;
	
	
	private String content;
	
	
	

}
