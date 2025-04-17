package com.codewithkrish.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.ApiResponse;
import com.codewithkrish.blog.payloads.UserDto;
import com.codewithkrish.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	public UserService userService;
	
	//post-create User
	// DTO BANNE  KA Reason ye hai ki ham apne user enetity o direct expose na kre 
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto userDtoSuccess=this.userService.createUser(userDto);
		
		
	
		return new ResponseEntity<>(userDtoSuccess,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id")Integer id)
	{
		UserDto userDtoSuccess=null;
		try {
			userDtoSuccess = this.userService.UpdateUser(userDto,id);
			
			

			return new ResponseEntity<>(userDtoSuccess,HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
          
			return  new  ResponseEntity<ApiResponse>(new ApiResponse("user not update beacuse it not exist",false),HttpStatus.BAD_REQUEST);
			
		}
		
		
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")   //iss annaothe ke lye hea @enablemethod security lagaya tha hamen
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id")Integer id)
	{
	
		try {
			this.userService.deleteUser(id);
			
			
  //Map return 
			//return  new  ResponseEntity(Map.of("message","user delted successfully"),HttpStatus.ACCEPTED);
			
		
//par ham map ko use na krke khudi banyi class ko bhejga
			
			return  new  ResponseEntity<ApiResponse>(new ApiResponse("user delted successfully",true),HttpStatus.ACCEPTED);
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
			
			return  new  ResponseEntity<ApiResponse>(new ApiResponse("user not exist",false),HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	
	
		@GetMapping("/{id}")
		public ResponseEntity<?> getUserById(@PathVariable("id")Integer id)
		{
		
			try {
			UserDto userdto	=this.userService.getUserById(id);
				
				

				return new ResponseEntity<>(userdto,HttpStatus.ACCEPTED);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				return  new  ResponseEntity<ApiResponse>(new ApiResponse("user not exist",false),HttpStatus.BAD_REQUEST);
				
			}
			
			
			

	}
		
		@GetMapping("/")
		public ResponseEntity<List<UserDto>> getAllUsers()
		{
		
			
			List<UserDto> userdto	=this.userService.getAllUsers();
				
				

				return new ResponseEntity<>(userdto,HttpStatus.ACCEPTED);
				
		 
			
			

	}
	
		
	
		
		
		
		
	
	

}
