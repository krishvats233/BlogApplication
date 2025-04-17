package com.codewithkrish.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.stereotype.Service;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.exception.ResourceNotFoundException;

import com.codewithkrish.blog.payloads.PostDto;
import com.codewithkrish.blog.payloads.PostResponse;
import com.codewithkrish.blog.repositeres.CategoryRepo;
import com.codewithkrish.blog.repositeres.PostRepo;
import com.codewithkrish.blog.repositeres.UserRepo;
import com.codewithkrish.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	public ModelMapper modelMapper;
	@Autowired
	public PostRepo postRepo;
	@Autowired
	public CategoryRepo categoryRepo;
	@Autowired
	public UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto PostDto,Integer user_Id,Integer category_id)throws ResourceNotFoundException
	{
		// TODO Auto-generated method stub
		
		Post post=this.postDtoToPost(PostDto);

	    post.setImageName("default.png");
	    post.setAddedDate(new Date());
	    Category category= this.categoryRepo.findById(category_id).orElseThrow((()-> new ResourceNotFoundException("Category","CategoryId", category_id)));
	    
	    
	    post.setCategory(category);
	    User user=this.userRepo.findById(user_Id).orElseThrow(()-> new ResourceNotFoundException("User","userId", user_Id));
	    post.setUser(user);
	    
	    Post addedPost=this.postRepo.save(post);
	    System.out.println(addedPost);
		return this.postToPostDto(addedPost);
	}

	
	
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","postId", id));
		  
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAddedDate(new Date());
		post.setImageName(postDto.getImageName());
		
		Post updatePost=this.postRepo.save(post);
		
		return this.postToPostDto(updatePost) ;
	}

	@Override
	public PostDto getPostById(Integer postId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		 Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId", postId));
		   
		
		return  this.postToPostDto(post);
	}

     @Override
	public PostResponse getAllPost(Pageable pageable) {
		// TODO Auto-generated method stub
		
		Page<Post>page=this.postRepo.findAll(pageable);
		
		 List<PostDto>postDto=page.getContent().stream().map((post)->this.postToPostDto(post)).collect(Collectors.toList());
			
		 PostResponse postResponse=new PostResponse(postDto,page.getNumber(),page.getSize(),(int)page.getTotalElements(),page.getTotalPages(),page.isLast());
			
		  
		 
			return postResponse;

	}

	@Override
	public void deletePost(Integer postId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		  
		this.postRepo.delete(post);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	public Post postDtoToPost(PostDto postDto)
	{
		
		
		Post post=this.modelMapper.map(postDto,Post.class);
		return post;
		
		
	}
	
	
	public PostDto  postToPostDto  (Post post)
	{
		
		
		PostDto postDto=this.modelMapper.map(post,PostDto.class);
		
		return postDto;
		
		
	
	
	
	
	
	


}

	
	
	@Override
	public List<PostDto> searchPostDto(String keyword) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		List<Post>post=this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto>postDto=post.stream().map((post1)->this.postToPostDto(post1)).collect(Collectors.toList());
		
		
		
		
		
		
		return postDto;
	}




	@Override
	public List<PostDto> getPostByCategory(Integer category_id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		Category category= this.categoryRepo.findById(category_id).orElseThrow((()-> new ResourceNotFoundException("Category","CategoryId", category_id)));
	    
		List<PostDto>postDto=category.getPosts().stream().map((post)->this.postToPostDto(post)).collect(Collectors.toList());
		
		
		return postDto;
	}




	@Override
	public List<PostDto> getPostByUser(Integer user_id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		 User user=this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User","userId", user_id));
		 
		 List<PostDto>postDto=user.getPosts().stream().map((post)->this.postToPostDto(post)).collect(Collectors.toList());
			
			
			return postDto;
		 
		
	}
	
	
}