package com.org.blog_api.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDto {

	private int id;
	@NotEmpty
	@Size(min = 5 , message = "must be graeter then 5 char")
	private String title;
	@NotEmpty
	@Size(min=10 ,message = "must be greater than 10 char")
	private String description;

}
