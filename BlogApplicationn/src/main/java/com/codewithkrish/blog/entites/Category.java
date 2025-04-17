package com.codewithkrish.blog.entites;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category 
{   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
     
	@Column(nullable = false)
	private String cateogry_Title;
	private String cateogry_description;
	
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,fetch =FetchType.LAZY )
	private List<Post> posts=new ArrayList<>();
	

}
