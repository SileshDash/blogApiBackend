package com.org.blog_api.service;

import java.util.List;

import com.org.blog_api.payload.CatagoryDto;

public interface catagoryService {
	CatagoryDto saveCatagory(CatagoryDto catagoryDto);
	CatagoryDto updateCatagory(CatagoryDto catagoryDto, Integer catagoryId);
	void deleteCatagory(Integer catagoryId);
	List<CatagoryDto> getAllCatagory();
	CatagoryDto getCatagoryById(Integer id);
}
