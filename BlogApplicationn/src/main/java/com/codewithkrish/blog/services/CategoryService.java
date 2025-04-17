package com.codewithkrish.blog.services;

import java.util.List;

import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.CategoryDto;
import com.codewithkrish.blog.payloads.UserDto;

public interface CategoryService {
	
	
	
	  
public CategoryDto createCategory(CategoryDto categoryDto);
	
public CategoryDto updateCategory(CategoryDto categorDto,Integer id) throws ResourceNotFoundException ;
	
public CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException ;
	
public List<CategoryDto> getAllCategory();
	
public void deleteCategory(Integer categoryId) throws ResourceNotFoundException;
	
	

}
