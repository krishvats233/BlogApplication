package com.codewithkrish.blog.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import com.codewithkrish.blog.entites.Comment;
import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
 public class UserDto 

{
	
	
	
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="Username must be of min 4 characrter")
	private String name;
	
	@Email

	private String email;
	
	@NotEmpty
	@Size(min=3,max=20,message="Password must be min of 3 character and max 10 character")

	private String password;
	
	@NotNull
	@Length(max = 100,min=10)
	private String about;
	
	
	private Set<RoleDto>roles=new HashSet<>();
	
	

}
