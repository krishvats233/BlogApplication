package com.codewithkrish.blog.secuity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codewithkrish.blog.entites.User;

public class CustomUserDetail implements UserDetails
{
	
	private User user;//iske object ke liye we use not @autowire by we use getter setter

	public CustomUserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomUserDetail(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// VVip to study about map function its return value()
		List<SimpleGrantedAuthority>simpleGrantedAuthorities=user.getRoles().stream().map(role ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return simpleGrantedAuthorities ;
				
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
