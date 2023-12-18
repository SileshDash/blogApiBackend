package com.org.blog_api.service;

import java.util.List;

import com.org.blog_api.payload.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer catagoryId);
	PostDto updatePost (PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	PostDto getPostById(Integer postId);
	List<PostDto> getAllPostByCatagory(Integer catagoryId);
	List<PostDto> getAllPostByUser(Integer userId);
	List<PostDto> searchPost(String keyWord);
}
