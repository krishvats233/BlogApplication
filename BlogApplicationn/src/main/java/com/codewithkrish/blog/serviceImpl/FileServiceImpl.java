package com.codewithkrish.blog.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithkrish.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService
{
	
	
	
	
	
	 @Override
	    public InputStream getResource(String path, String fileName) throws Exception
	    {
	        String fullPath=path+File.separator+fileName;
	        InputStream is=new FileInputStream(fullPath);



	        return is;
	    }

	    @Override
	    public String uploadImage(String path, MultipartFile file) throws IOException {
	        //ye path images tak ka hai

	        //fileName

	        String name= file.getOriginalFilename();



//	        //Now we manage if user eneter same for the feild so we add an id in it
	//
//	        String random= UUID.randomUUID().toString();
	//
//	        //ex file ka name abc.png tha ab usse abc123.png
//	        String  fileName=name.substring(0,name.lastIndexOf("."))+random+name.substring(name.lastIndexOf("."));





	        //FullPATH

	        String filePath=path+ File.separator+name;




	    /*----------------------------------------------------------*/

	        File file1= new File(path);
	        if(!file1.exists())
	        {
	            file1.mkdir();

	        }

	            long copy = Files.copy(file.getInputStream(), Paths.get(filePath));



	        //Create folder of image


	        return name;
	    }
	
	
	
	

}
