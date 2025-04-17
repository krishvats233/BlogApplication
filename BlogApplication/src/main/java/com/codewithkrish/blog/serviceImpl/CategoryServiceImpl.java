package com.codewithkrish.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.CategoryDto;
import com.codewithkrish.blog.payloads.UserDto;
import com.codewithkrish.blog.repositeres.CategoryRepo;
import com.codewithkrish.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	public CategoryRepo categoryRepo;
	
	//create
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryDtoToCategory(categoryDto);
		Category addedCat=this.categoryRepo.save(cat);
		
		return this.categoryToCategoryDto(addedCat);
	}
	
	
	//update

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) throws ResourceNotFoundException  {
		// TODO Auto-generated method stub
		
		
		Category cat=this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", id));
		
		cat.setCateogry_description(categoryDto.getCateogry_description());
		cat.setCateogry_Title(categoryDto.getCateogry_Title());
		
		Category addedCat=this.categoryRepo.save(cat);
		return this.categoryToCategoryDto(addedCat);
		
		
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", categoryId));
		
		return this.categoryToCategoryDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		

		
		List<Category>cat=this.categoryRepo.findAll();
		List<CategoryDto> categoryDto=cat.stream().map((category)->this.categoryToCategoryDto(category)).collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", categoryId));
		
		this.categoryRepo.delete(cat);
		
		
	}
	
	public Category categoryDtoToCategory(CategoryDto categoryDto)
	{
		
		
		Category category=this.modelMapper.map(categoryDto,Category.class);
		return category;
		
		
	}
	
	
	
	public CategoryDto categoryToCategoryDto(Category category)
	{
		
		

		CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
		return categoryDto;
		
	}
	


}
