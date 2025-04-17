package com.codewithkrish.blog.payloads;

import java.util.Date;
import java.util.List;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.entites.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostResponse {
	
	private List<PostDto> allPosts;
	
	private int pageNumber;
	
	private int pageSize;
	
	private int totalElements;
	
	private int totalPages;
	
	private boolean lastPage; //here we check wheter the current page is lat page or not

}
