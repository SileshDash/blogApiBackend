package com.org.blog_api.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.blog_api.payload.UserDto;
import com.org.blog_api.service.userService;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired(required = true)
	private userService userService;
	
@PostMapping("/save")
public ResponseEntity<UserDto> saveUser(@Valid@RequestBody UserDto userDto){
	UserDto saveUser =userService.saveUser(userDto);
	return new ResponseEntity<UserDto>(saveUser,HttpStatus.CREATED);
}
@PutMapping("/update/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
	UserDto updatedUser=this.userService.updateUser(userDto, userId);
	return ResponseEntity.ok(updatedUser);
	}

@DeleteMapping("/delete/{userId}")
public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
	 this.userService.deleteUser(userId);
	 return new ResponseEntity(Map.of("message","User deleted sucessfully"),HttpStatus.OK);
}
@GetMapping("/get")
public ResponseEntity<List<UserDto>> getAllUser(){

	return ResponseEntity.ok(userService.getAll());
}
@GetMapping("/get/{userId}")
public ResponseEntity<UserDto> getUserById( @ Nullable @PathVariable Integer userId){
	
	return ResponseEntity.ok(this.userService.getUserById(userId));
}
}