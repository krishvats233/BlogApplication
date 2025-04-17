package com.codewithkrish.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends Exception 
{
	
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String FeildName;
	private int feildvalue;
	public ResourceNotFoundException(String resourceName, String feildName, int feildvalue) {
		super(String.format("%s not found with this %s : %d",resourceName,feildName,feildvalue));
		this.resourceName = resourceName;
		FeildName = feildName;
		this.feildvalue = feildvalue;
	}
	
	
	
	
	

}
