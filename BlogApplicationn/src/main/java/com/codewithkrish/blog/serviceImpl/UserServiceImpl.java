package com.codewithkrish.blog.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import com.codewithkrish.blog.exception.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithkrish.blog.config.AppConstant;
import com.codewithkrish.blog.entites.Role;
import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.payloads.UserDto;
import com.codewithkrish.blog.repositeres.RoleRepo;
import com.codewithkrish.blog.repositeres.UserRepo;
import com.codewithkrish.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		  
		User user=this.dtoToUser(userDto);
		 user.setPassword(encoder.encode(userDto.getPassword()));
		 
		  Role role=this.roleRepo.findById(AppConstant.ADMIN).get();
		  
		  user.getRoles().add(role);
	 
		User savedUser=this.repo.save(user);
			
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto UpdateUser(UserDto userDto,Integer id) throws ResourceNotFoundException  {
		// TODO Auto-generated method stub
		
	
			User user=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id ",id));
		 user.setName(userDto.getName());
		 user.setPassword(encoder.encode(userDto.getPassword()));
		 user.setEmail(userDto.getEmail());
		 user.setAbout(userDto.getAbout());
		 User savedUser=this.repo.save(user);
		
		 return this.userToDto(savedUser);
		 
				 
			
			
			
}
		
		
	

	@Override
	public UserDto getUserById(Integer userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		
		User user=this.repo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id ",userId));
		 
		
		return this.userToDto(user);
		 
		
		
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		
List<User> user=this.repo.findAll();
		
  List<UserDto> userDto=user.stream().map(user1->this.userToDto(user1)).collect(Collectors.toList());
		 

		return userDto;
		
	}

	@Override
	public void deleteUser(int userId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		
		User user=this.repo.findById(userId).orElseThrow(()->  new ResourceNotFoundException("User", "id ",userId));
		  
		
		
		 
		
		this.repo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto)
	{
		
		
			User user=this.modelMapper.map(userDto,User.class);//1st paramter object jiise convert krna hai 2nd paramter name of class jisme krna ha
		
		
		
			
			
			
			//manual approact
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		
		
		
		return user;
	}
	
	
	
	
	public UserDto userToDto(User user)
	{
		
		
		
		
		UserDto userDto=this.modelMapper.map(user,UserDto.class);//1st paramter object jiise convert krna hai 2nd paramter name of class jisme krna ha
		
		
		
		
		
		
		//Manual approach
//		
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		
		
		
		return userDto;
	}

	@Override
	public UserDto RegisterNewUser(UserDto userDto) {
		
		User user=this.modelMapper.map(userDto,User.class);
		//ab isko save krte time 2 cheezo ka khayal rakna ha
		//1st iske password ka
		//2nd iske role ka
		user.setPassword(this.encoder.encode(user.getPassword()));
		
		//roles
		//by default agr koi bhi register api ke through aarha hai tho vo normal user hai
		   
		  Role role=this.roleRepo.findById(AppConstant.NORMAL).get();
		  
		  user.getRoles().add(role);
		  
		  User savedUser=this.repo.save(user);
		  
		
		return this.modelMapper.map(savedUser,UserDto.class);
	}
	
	
	
	
	
	
	

}
