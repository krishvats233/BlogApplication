package com.codewithkrish.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.UserDto;



public interface UserService
{  
	
	UserDto RegisterNewUser(UserDto user);
	
	
	UserDto createUser(UserDto userDto);
	
	UserDto UpdateUser(UserDto userDto,Integer id) throws ResourceNotFoundException;
	
	UserDto getUserById(Integer userId) throws ResourceNotFoundException;
	
	List<UserDto> getAllUsers();
	
	void deleteUser(int userId) throws ResourceNotFoundException;
	
	


}
