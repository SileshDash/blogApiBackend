package com.org.blog_api.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	@Size(min = 8, message = "email should not be less than 8")
	private String email;
	@NotEmpty
	@Size(min = 5, max = 10, message = "message should not be less than 5 or more than 10")
	private String password;
	@NotEmpty
	private String about;
	
	

	
}

