package com.codewithkrish.blog.serviceImpl;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithkrish.blog.entites.Comment;
import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.CommentDto;
import com.codewithkrish.blog.repositeres.CommentRepo;
import com.codewithkrish.blog.repositeres.PostRepo;
import com.codewithkrish.blog.repositeres.UserRepo;
import com.codewithkrish.blog.services.CommentService;

import lombok.Setter;

@Service
public class CommentServiceImpl implements CommentService {
	
	  @Autowired
	  public PostRepo postRepo;
	  
	  @Autowired
	  public UserRepo userRepo;
	
	  @Autowired
	  public CommentRepo commentRepo;
	  
	  @Autowired
		public ModelMapper modelMapper;
	
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) throws Exception{
	       
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post id : ",postId));
	     
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id : ",userId));
		
		Comment comment=commentDtoToComment(commentDto);
		
	    comment.setPost(post);  
	    comment.setUser(user);
		   
	    this.commentRepo.save(comment);
		
		return this.commentToCommentDto(comment);
	}

	@Override
	public void deleteComment(Integer commentId) throws Exception {
		// TODO Auto-generated method stub
		
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment"," comment id: ", commentId));
            
		
		this.commentRepo.delete(comment);
	}
	
	
	
	
	public Comment commentDtoToComment(CommentDto commentDto)
	{
		
		
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		return comment;
	

}
	
	
	public CommentDto commentToCommentDto(Comment comment)
	{
		
		
		CommentDto commentDto=this.modelMapper.map(comment,CommentDto.class);
		return commentDto;
	

}



}
