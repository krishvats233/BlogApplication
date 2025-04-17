package com.codewithkrish.blog.controllers;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithkrish.blog.payloads.FileResponse;
import com.codewithkrish.blog.config.AppConstant;
import com.codewithkrish.blog.entites.Post;
import com.codewithkrish.blog.entites.User;
import com.codewithkrish.blog.exception.ResourceNotFoundException;
import com.codewithkrish.blog.payloads.ApiResponse;
import com.codewithkrish.blog.payloads.PostDto;
import com.codewithkrish.blog.payloads.PostResponse;
import com.codewithkrish.blog.services.FileService;
import com.codewithkrish.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class PostController 
{
    @Value("${project.image}")
    private String path;
	
	@Autowired
	public PostService postService;
	
	@Autowired
	public FileService fileService;
	

	
	
	@PostMapping("/user/{user_id}/category/{category_id}/posts")
	public ResponseEntity<?>createPost(@Valid @RequestBody PostDto postDto,@PathVariable("user_id")Integer user_id,@PathVariable("category_id")Integer category_id) {
		
		
			try {
				PostDto postDtoSuccess=this.postService.createPost(postDto,user_id,category_id);
				  
				return new ResponseEntity<PostDto>(postDtoSuccess,HttpStatus.CREATED);
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println( e);
				
				return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
			}
		     
			
			
		
		
		
		
	}
	
	
	
	//@GET post by user
	
	@GetMapping("/user/{user_id}/posts")
	public ResponseEntity<?>getPostsByUser(@PathVariable("user_id")Integer user_id) throws ResourceNotFoundException {
		
			List<PostDto> postDtoSuccess=this.postService.getPostByUser(user_id);
			
		
	     return new ResponseEntity<List<PostDto>>(postDtoSuccess,HttpStatus.ACCEPTED);
		
		
	
	     
	     
	     
		  
//				return new ResponseEntity<PostDto>(postDtoSuccess,HttpStatus.CREATED);
//			} catch (ResourceNotFoundException e) {
//				// TODO Auto-generated catch block
//				System.out.println( e);
//				
//				return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
//			}
	     
	
	
	
}

	
	
	
	
	//@GET post by category
	
		@GetMapping("/category/{category_id}/posts")
		public ResponseEntity<?>getPostsByCateegory(@PathVariable("category_id")Integer category_id) throws ResourceNotFoundException {
			
				List<PostDto> postDtoSuccess=this.postService.getPostByCategory(category_id);
				
			
		     return new ResponseEntity<List<PostDto>>(postDtoSuccess,HttpStatus.ACCEPTED);
			
			
		
		
	
	
	
	

}
		
//get all post
@GetMapping("/posts")
public ResponseEntity<?>getAllPosts(@RequestParam(
		value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required= false)int pageNumber,
		@RequestParam(value="postSize",defaultValue=AppConstant.PAGE_SIZE,required=false)int pageSize,
		@RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false)
String sortBy,@RequestParam(value="sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir)
{  	org.springframework.data.domain.Pageable pageable=null;
   /*VVIP PAGENUMBER start from zero*/
	if(sortDir.equals("asc"))
	{	
       pageable=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
	}
	else {
		 pageable=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
		
	}
	
	
	PostResponse postResponse=this.postService.getAllPost(pageable);
		
		
		
	 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.ACCEPTED);
	
	 
	

}



//getPOstById
@GetMapping("/post/{post_id}/posts")
public ResponseEntity<?>getPostById(@PathVariable("post_id")Integer post_id) throws ResourceNotFoundException
{
	 PostDto postdto=this.postService.getPostById(post_id);
	
	
	 return new ResponseEntity<PostDto>(postdto,HttpStatus.ACCEPTED);
		
}

@PutMapping("/post/{post_id}/posts")
public ResponseEntity<?>updatePost(@RequestBody PostDto postDto,@PathVariable("post_id")Integer post_id) throws ResourceNotFoundException
{
	 PostDto postdto=this.postService.updatePost(postDto,post_id);
	
	 ApiResponse api=new ApiResponse("Post is update #### ",true);
		
		
	 return new ResponseEntity<ApiResponse>(api,HttpStatus.ACCEPTED);
		
		
}

@DeleteMapping("/post/{post_id}/posts")


public ResponseEntity<ApiResponse>deletePost(@PathVariable("post_id")Integer post_id) throws ResourceNotFoundException
{
	 this.postService.deletePost(post_id);
	 ApiResponse api=new ApiResponse("Post is Delted !!! ",true);
	
	
	 return new ResponseEntity<ApiResponse>(api,HttpStatus.ACCEPTED);
		
}


//SEARCH
@GetMapping("/posts/search/{keyword}")
public ResponseEntity<?>searchPostByTitle(@PathVariable("keyword") String keyword) throws ResourceNotFoundException
{
	List<PostDto>postDtos=this.postService.searchPostDto(keyword);
	
	
	/* iska use jab ham @Query ka use krenge
	 * 
	 *  
	List<PostDto>postDtos=this.postService.searchPostDto("%"+keyword+"%");*/
	
	
	
	
	return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
	
}





@PostMapping("/post/image/upload/{postId}")//isse ham ye pata lagyege kiss post ki image hai
public ResponseEntity<?> imageUpload(@RequestParam("image")MultipartFile image,@PathVariable("postId") int postId)
{

String fileName= null;
try {
	
    //is post ki id sabse phle get ki taki ga vo id ka post na ho ho tho pjle hea rescource not found ka exception ajjaye

    PostDto postDto=this.postService.getPostById(postId);
    

       
    fileName = this.fileService.uploadImage(path,image);
    System.out.println(image.getContentType()  +"    image ka content type");
    
    
  //now save this file name in post table by post servcie
    
    postDto.setImageName(fileName);
    
    PostDto updatePost=this.postService.updatePost(postDto,postId);




} catch (Exception e) {


    return new ResponseEntity<>(new com.codewithkrish.blog.payloads.FileResponse(null,"Image not Uploaede !!!"),HttpStatus.INTERNAL_SERVER_ERROR);
}




return new ResponseEntity<>(new com.codewithkrish.blog.payloads.FileResponse(fileName,"Image is Uploaed Successfuuly"),HttpStatus.CREATED);
}





@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)//prcoducces s just a extra information
    public void getImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException {  InputStream Is;
    //response mai img type ka data bhejne ke lye httpservlet response ka use
    try {
         Is =this.fileService.getResource(path,imageName);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    //tho ab Is ke input stream mai jo value hai usse response ke outputStream mai copy kregfe
    StreamUtils.copy(Is,response.getOutputStream());



}






}
