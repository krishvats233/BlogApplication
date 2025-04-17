package com.codewithkrish.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithkrish.blog.entites.Comment;
import com.codewithkrish.blog.payloads.ApiResponse;
import com.codewithkrish.blog.payloads.CommentDto;
import com.codewithkrish.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController 
{   
	@Autowired
	public CommentService commentService;
	
	
	
	@PostMapping("/post/{post_id}/user/{user_id}/comments")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto,@PathVariable("post_id")int post_id,@PathVariable("user_id")int user_id) throws Exception
	{    
		

	      
		
		CommentDto savedComment=this.commentService.createComment(commentDto,post_id,user_id);
		
		 
		 return new ResponseEntity<CommentDto>(savedComment,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/comment/{comment_id}")
	public ResponseEntity<?>deleteComment(@PathVariable("comment_id")int comment_id) throws Exception
	{    
		
		
		
		this.commentService.deleteComment(comment_id);
		
		 
		 return new ResponseEntity<>(new ApiResponse("Cooment Deleted Sucessfully !!!",true),HttpStatus.ACCEPTED);
		
	}
	

}
