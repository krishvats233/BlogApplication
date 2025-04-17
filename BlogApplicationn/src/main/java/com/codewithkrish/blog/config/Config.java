package com.codewithkrish.blog.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

import com.codewithkrish.blog.secuity.UserDetailServiceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration

public class Config 
{ 
	
	
	
	
	     @Bean
		 public UserDetailsService getUserDetailsService()
		{
		return new UserDetailServiceImpl();}
		
	

		 
	

	    @Bean
	    public PasswordEncoder getPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	  
	    @Bean
		 public DaoAuthenticationProvider daoauthenticationProvider()
		 { DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		     daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		     daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
			 return daoAuthenticationProvider;
		 }
	 

		 @Bean
		 public AuthenticationProvider authenticationProvider()
		 {
			 return daoauthenticationProvider();
		 }

		 
		 
		 //iss bean ki neee padti hai password vagra ko autenticate ke liye
		 @Bean
	        public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	            return builder.getAuthenticationManager();
	        }
	

}
