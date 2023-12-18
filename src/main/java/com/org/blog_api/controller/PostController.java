package com.org.blog_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
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
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId){
		List<PostDto> posts=this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts ,HttpStatus.OK);
	}
	@GetMapping("user/{catagoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCatagory(@PathVariable Integer catagoryId){
		List<PostDto> posts=this.postService.getAllPostByCatagory(catagoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = "10",required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){
		List<PostDto> posts=this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@RequestBody PostDto postDto){
		PostDto updatedPostDto=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK );
	}
	
}
