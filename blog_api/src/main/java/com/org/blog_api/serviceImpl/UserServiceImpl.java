package com.org.blog_api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.blog_api.entity.User;
import com.org.blog_api.exception.ResourceNotFoundException;
import com.org.blog_api.payload.UserDto;
import com.org.blog_api.repository.userRepo;
import com.org.blog_api.service.userService;

@Service
public class UserServiceImpl implements userService {
   @Autowired
	private userRepo userRepo;
   @Autowired
  private ModelMapper modelMapper;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user= this.userDtoToUser(userDto);
		User savedUser= this.userRepo.save(user);
		return this.UserToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
	user.setName(userDto.getName());
	user.setEmail(userDto.getEmail());
	user.setPassword(userDto.getPassword());
	user.setAbout(userDto.getAbout());
	
	User updatedUser=this.userRepo.save(user);
	UserDto updatedUserDto=this.UserToUserDto(updatedUser);
	return updatedUserDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAll() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> dtos=users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
		return dtos;
	}

	public User userDtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
		return user;	
	}
	public UserDto UserToUserDto(User user) {
		UserDto userDto= this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
