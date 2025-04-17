package com.codewithkrish.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse {
	private String message;
	private boolean success;//True hogyi tho req success otherwsie false tho nhi hui

}
