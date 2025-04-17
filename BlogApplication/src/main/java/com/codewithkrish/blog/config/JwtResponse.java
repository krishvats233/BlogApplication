package com.codewithkrish.blog.config;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class JwtResponse {
    //respnse mai sirf ham information leta hai user ki   token bhejta hai

    private  String jwtToken;
    private  String username;

}
