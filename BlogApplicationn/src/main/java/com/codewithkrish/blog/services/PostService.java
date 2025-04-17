package com.codewithkrish.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.PostDto;
import com.codewithkrish.blog.payloads.PostResponse;


public interface PostService {
	
	
	public PostDto createPost(PostDto PostDto,Integer user_Id,Integer category_id)throws ResourceNotFoundException;
	
	public PostDto updatePost(PostDto PostDto,Integer id) throws ResourceNotFoundException ;
		
	public PostDto getPostById(Integer postId) throws ResourceNotFoundException ;
		
	public PostResponse getAllPost(Pageable pageable);
		
	public void deletePost(Integer postId) throws ResourceNotFoundException;
	
	public List<PostDto> getPostByCategory(Integer category_id)throws ResourceNotFoundException;
	
	public List<PostDto> getPostByUser(Integer user_id)throws ResourceNotFoundException; 
		
    public List<PostDto> searchPostDto(String keyword) throws ResourceNotFoundException ;	
	
	
	
	
	
	

}
