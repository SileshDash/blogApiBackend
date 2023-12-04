package com.org.blog_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.blog_api.entity.Catagory;
import com.org.blog_api.entity.Post;
import com.org.blog_api.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCatagory(Catagory catagory);
}
