package com.codewithkrish.blog.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithkrish.blog.payloads.ApiResponse;


//iis wali annaotation ki help se ham global exception handling kregnge

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>resourceNotFoundException( ResourceNotFoundException ex)//isme hame object bhi mil jayga is class ka jab exception aayag
	{
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
	
		return new ResponseEntity<ApiResponse>(api,HttpStatus.BAD_REQUEST);
		
		
	}

	
	
	/*ye jab chalega jab bean mai validation aur wrong dat jayga*/
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>methodArgumentNotValidException( MethodArgumentNotValidException ex)//isme hame object bhi mil jayga is class ka jab exception aayag
	{
		
		Map<String,String>resp=new HashMap<String, String>();
		
	ex.getBindingResult().getAllErrors().forEach(error ->{
		String feildName=((FieldError)error).getField();
		String feildMessage =error.getDefaultMessage();
		resp.put(feildName, feildMessage);
		
	});
		
		
		
		return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
		
		
	
	
	
}
	
/*ye jab chalega jab emai ko unique set kiya hai aur ham duplicate email bheja*/
	
	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
	public ResponseEntity<?>UnexpectedTypeException(org.springframework.dao.DataIntegrityViolationException ex)//isme hame object bhi mil jayga is class ka jab exception aayag
	{
		 
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(api,HttpStatus.BAD_REQUEST);
		
	
	
	
}
	
	
/*ye jab chalega jab emai ko unique set kiya hai aur ham duplicate email bheja*/
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?>apiException(ApiException ex)//isme hame object bhi mil jayga is class ka jab exception aayag
	{
		 
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(api,HttpStatus.BAD_REQUEST);
		
	
	
	
}
	
	
	
	
	
	
	
	
	
}
