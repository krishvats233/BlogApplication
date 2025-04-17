package com.codewithkrish.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.entites.Comment;
import com.codewithkrish.blog.entites.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private int postId;
	
	/* cateogry_id and user_id ko ham url se mangwa lege */
	@NotEmpty
	@Length(max=100,min=0,message="Content lenth is between 0 to 100")
	private String content;
	
	

	@NotEmpty
	@Length(max=100,min=10,message="Content lenth is between 0 to 20")
	private String title;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;  

	private String imageName;
	
	//isse fayda hoga jab ham post ko ftech krga tho uska comments bhi aajygae
	private List<CommentDto>comments=new ArrayList<>();
	
	
	
	
	
	
	
	

}
