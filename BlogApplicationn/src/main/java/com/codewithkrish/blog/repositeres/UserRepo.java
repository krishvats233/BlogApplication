package com.codewithkrish.blog.repositeres;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithkrish.blog.entites.User;

public interface UserRepo extends JpaRepository<User,Integer> 
{
	Optional<User> findByEmail(String email); //yha se optional return krnge apne custom
	//emthod ke liya tabhi wha orElsethROw use kr payge

}
