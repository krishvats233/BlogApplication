package com.codewithkrish.blog.services;

import com.codewithkrish.blog.payloads.CommentDto;

public interface CommentService 
{
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId)throws Exception;
	 
	
	void deleteComment(Integer commentId)throws Exception;
	

}
