package com.org.blog_api.payload;

import java.util.Date;

import com.org.blog_api.entity.Catagory;
import com.org.blog_api.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	@NotEmpty
	private String title;
	@NotEmpty
	@Size(min = 8,message = "content shouls be more than 8 character")
	private String content;
	private String imageName;
	private Date addDate;
	private UserDto user;
	private CatagoryDto catagory;
	
	
}
