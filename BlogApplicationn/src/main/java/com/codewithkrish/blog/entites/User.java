package com.codewithkrish.blog.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_email",columnNames = "email"))
public class User 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name",nullable = false,length = 100)
	
	private String name;
	
	@Column(unique = true)

	private String email;
	
	private String password;
	
	private String about;
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,fetch =FetchType.LAZY )
	private List<Comment> comments=new ArrayList<>();

	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,fetch =FetchType.LAZY )
	private List<Post> posts=new ArrayList<>();
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name ="user_role",joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))//referedcoloum name mean iss table ka konsa coloum act as foreign key in thir table
	private Set<Role>roles=new HashSet<>();//asa value mean new Hashset() krne se ham avoid krte hai null pointer exception
	
	
	
	
	/*
	 * one thing  must be in mind the name of class attribute like User ke attribute and its Dto attribute msut be same 
	 * 
	 * that help MODEL MAPPER to set their value
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
}
