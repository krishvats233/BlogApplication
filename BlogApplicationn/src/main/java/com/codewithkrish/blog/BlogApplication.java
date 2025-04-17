package com.codewithkrish.blog;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithkrish.blog.config.AppConstant;
import com.codewithkrish.blog.entites.Role;
import com.codewithkrish.blog.repositeres.RoleRepo;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	
	@Bean
 public ModelMapper modelMapper()
 {

		return new ModelMapper();
 }

	
	
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(passwordEncoder.encode("trilokeshwar@29"));
		try {
			
			//create role for first time start of the application
			Role role=new Role();
			role.setId(AppConstant.ADMIN);
			role.setName("ROLE_ADMIN");
			
			
//			this.roleRepo.save(role);
			
			//create role for first time start of the application
			
			Role role1=new Role();
			role1.setId(AppConstant.NORMAL);
			role1.setName("ROLE_NORMAL");
			  List<Role>roles=List.of(role,role1);
			
			List<Role>saveRole=this.roleRepo.saveAll(roles); 
			
			saveRole.forEach(rolee -> System.out.println(rolee));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	
		
	}

}
