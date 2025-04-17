package com.codewithkrish.blog.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Comment 
{   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String content;
	
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	
	
	//user se realtionship khud kro sir post wala karygar
	
	
	//isme 2 cheeza hogi ek tho kiss user ne comment kiya hai 
	//aur dusra kiss post pai comment kiya hai
	
	
	
	
	
	
	
	

}
