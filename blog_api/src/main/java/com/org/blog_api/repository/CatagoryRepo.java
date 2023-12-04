package com.org.blog_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.blog_api.entity.Catagory;

public interface CatagoryRepo extends JpaRepository<Catagory, Integer> {

}
