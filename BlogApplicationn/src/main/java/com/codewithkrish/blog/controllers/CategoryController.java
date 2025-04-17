package com.codewithkrish.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.ApiResponse;
import com.codewithkrish.blog.payloads.CategoryDto;
import com.codewithkrish.blog.payloads.UserDto;
import com.codewithkrish.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	public CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
	   CategoryDto categoryDto2=this.categoryService.createCategory(categoryDto);
		
		
	
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	

}
	
	
	//update 

	@PutMapping("/{cid}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("cid") Integer cid )
	{
	   CategoryDto categoryDto2=null;
	try {
		
		
		categoryDto2 = this.categoryService.updateCategory(categoryDto,cid);

		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	} 
	
	catch (ResourceNotFoundException e) {
		// TODO Auto-generated catch block
      System.out.println(e);
      
		return  new  ResponseEntity<ApiResponse>(new ApiResponse("Category not update beacuse it not exist",false),HttpStatus.BAD_REQUEST);
		
      
	}
		
		
	
	

}
	
	
	
//Get by id

	@GetMapping("/{cid}")
	public ResponseEntity<?> getCategoryById(@PathVariable("cid") Integer cid )
	{
	   CategoryDto categoryDto2=null;
	try {
		
		
		categoryDto2 = this.categoryService.getCategoryById(cid);

		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.ACCEPTED);
	} 
	
	catch (ResourceNotFoundException e) {
		// TODO Auto-generated catch block
      System.out.println(e);
      
		return  new  ResponseEntity<ApiResponse>(new ApiResponse("Category not update beacuse it not exist",false),HttpStatus.BAD_REQUEST);
		
      
	}
	
	
	}
	
	
	
	
	
	//Get All

		@GetMapping("/")
		public ResponseEntity<?> getAllCategory( )
		{
		   List<CategoryDto>categoryDto2 = this.categoryService.getAllCategory();

			return new ResponseEntity<List<CategoryDto>>(categoryDto2,HttpStatus.ACCEPTED);
		
	

      }
	
		
		//delete by id

		@DeleteMapping("/{cid}")
		public ResponseEntity<?> deleteCategoryById(@PathVariable("cid") Integer cid )
		{
			
		try {
			
			
			
			this.categoryService.deleteCategory(cid);

			
			return  new  ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted successfully",true),HttpStatus.ACCEPTED);
			
			
			
		} 
		
		catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
	      System.out.println(e);
	      
			return  new  ResponseEntity<ApiResponse>(new ApiResponse("Category not update beacuse it not exist",false),HttpStatus.BAD_REQUEST);
			
	      
		}
		
	

	

}
		





}
