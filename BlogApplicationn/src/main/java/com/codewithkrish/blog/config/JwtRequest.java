package com.codewithkrish.blog.config;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class JwtRequest
{
    //isme 2 cheeza rakhdo username or password

    private String email;
    private String password;




}
