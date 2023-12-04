package com.org.blog_api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.blog_api.payload.PostDto;
import com.org.blog_api.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/catagory/{catagoryId}/posts")
	public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer catagoryId){
		PostDto createPost=this.postService.createPost(postDto, userId, catagoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity(Map.of("Message","Post Deleted Sucessfully"), HttpStatus.OK);
	}
}
