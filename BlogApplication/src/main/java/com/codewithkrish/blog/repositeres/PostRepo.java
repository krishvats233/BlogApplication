package com.codewithkrish.blog.repositeres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithkrish.blog.entites.Category;
import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	public List<Post> findByUser( User user);  //user ke sabhi post ko nikalne ke liye
	
	public List<Post> findByCategory( Category category);  //category ke sabhi post ko nikalne ke liye
	
	//ham title wise saerching implemenet krenge

	public List<Post> findByTitleContaining(@Param("key") String title);

	//vaisa tho hibernate quey khud likee run krega par ham bhi likh skte hai
	
	
	//	@Query("select p from Post p where p.title like:key")
	
}
