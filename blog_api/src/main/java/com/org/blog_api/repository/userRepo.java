package com.org.blog_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.blog_api.entity.User;

public interface userRepo extends JpaRepository<User, Integer> {

}
