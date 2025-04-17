package com.codewithkrish.blog.secuity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.repositeres.UserRepo;


public class UserDetailServiceImpl implements UserDetailsService
{  
	//ye hamne spring security ke liye banii hai jab bhi uusse hamne jo password diya hai usse secarh krna hai tab
	
	
    @Autowired
    public UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
	
		// TODO Auto-generated method stub
		
		User user=null;
	  try
	  
	  {	 user=this.repo.findByEmail(username).
				orElseThrow(() -> new ResourceNotFoundException(" User ", " email : "+username, 0));
	   
//	User user=optional.get();
	
	
	

	}
	  
	  catch(ResourceNotFoundException ex)
	  {
		 ex.printStackTrace();
	  }
	 
		return new CustomUserDetail(user);
	  
	}

}
