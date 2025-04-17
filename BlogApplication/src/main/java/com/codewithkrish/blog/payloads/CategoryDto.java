package com.codewithkrish.blog.payloads;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto 
{   
	private Integer category_id;
     
	@NotEmpty
	@Length(min =7,max = 100,message = " title must contain min 7 char and max 100 car")
	private String cateogry_Title;
	@NotEmpty
	@Length(min =7,max = 100,message = " descrption must contain min 7 char and max 100 car")
	private String cateogry_description;

}
