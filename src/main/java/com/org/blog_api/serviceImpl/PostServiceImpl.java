package com.org.blog_api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.org.blog_api.entity.Catagory;
import com.org.blog_api.entity.Post;
import com.org.blog_api.entity.User;
import com.org.blog_api.exception.ResourceNotFoundException;
import com.org.blog_api.payload.PostDto;
import com.org.blog_api.repository.CatagoryRepo;
import com.org.blog_api.repository.PostRepo;
import com.org.blog_api.repository.userRepo;
import com.org.blog_api.service.PostService;
@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private userRepo userRepo;
	@Autowired
	private CatagoryRepo catagoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId,Integer catagoryId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));
		Catagory catagory= this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory","catagory Id", catagoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCatagory(catagory);
		Post newPost = this.postRepo.save(post);
	
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(post.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
		
		Pageable pageable=PageRequest.of(pageNumber,pageSize);
		
		Page<Post> pagePost=this.postRepo.findAll(pageable);
		List<Post> allposts=pagePost.getContent();
		List<PostDto> postdtos=allposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByCatagory(Integer catagoryId) {
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory", "catagory ID", catagoryId));
		List<Post> posts= this.postRepo.findByCatagory(catagory);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
	User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
	List<Post> posts=this.postRepo.findByUser(user);
	List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
