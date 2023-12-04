package com.org.blog_api.service;

import java.util.List;

import com.org.blog_api.payload.UserDto;

public interface userService {
	UserDto saveUser(UserDto userDto);
	UserDto updateUser(UserDto user, Integer userId);
	void deleteUser(Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAll();

}
