package com.codewithkrish.blog.services;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	
	
    public String uploadImage(String Path, MultipartFile file) throws Exception;

  public  InputStream getResource(String path,String fileName) throws Exception;//name of file, aur kis jagaha pai meri file store hai



}
