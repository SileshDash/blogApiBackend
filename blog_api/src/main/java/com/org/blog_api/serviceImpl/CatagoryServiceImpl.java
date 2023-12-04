package com.org.blog_api.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.blog_api.entity.Catagory;
import com.org.blog_api.exception.ResourceNotFoundException;
import com.org.blog_api.payload.CatagoryDto;
import com.org.blog_api.repository.CatagoryRepo;

import com.org.blog_api.service.catagoryService;
@Service
public class CatagoryServiceImpl implements catagoryService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CatagoryRepo catagoryRepo;
	@Override
	public CatagoryDto saveCatagory(CatagoryDto catagoryDto) {
		Catagory catagory=this.modelMapper.map(catagoryDto, Catagory.class);
		Catagory savedCatagory=this.catagoryRepo.save(catagory);
		return this.modelMapper.map(savedCatagory, CatagoryDto.class);
	}
	@Override
	public CatagoryDto updateCatagory(CatagoryDto catagoryDto, Integer catagoryId) {
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", catagoryId));
		catagory.setTitle(catagoryDto.getTitle());
		catagory.setDescription(catagoryDto.getDescription());
		Catagory updatedCatagory=this.catagoryRepo.save(catagory);
		return this.modelMapper.map(updatedCatagory, CatagoryDto.class);
	}
	@Override
	public void deleteCatagory(Integer catagoryId) {
		Catagory catagory= this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", catagoryId));
		this.catagoryRepo.delete(catagory);
	}
	@Override
	public List<CatagoryDto> getAllCatagory() {
		List<Catagory>catagories =this.catagoryRepo.findAll();
		List<CatagoryDto> catagoryDtos=catagories.stream().map((cat)->this.modelMapper.map(cat, CatagoryDto.class)).collect(Collectors.toList());
		return catagoryDtos;
	}
	@Override
	public CatagoryDto getCatagoryById(Integer id) {
		Catagory catagory=this.catagoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", id));
		
		return this.modelMapper.map(catagory, CatagoryDto.class);
	}

//	@Override
//	public CatagoryDto saveCatagory(CatagoryDto catagoryDto) {
//		Catagory catagory=this.catagoryDtoToCatagory(catagoryDto);
//		Catagory savedCatagory=this.catagoryRepo.save(catagory);
//		return this.catagoryTocatagoryDto(savedCatagory);
//	}
//
//	@Override
//	public CatagoryDto updateCatagory(CatagoryDto catagoryDto, Integer catagoryId) {
//		Catagory catagory= this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", catagoryId));
//		catagory.setTitle(catagoryDto.getTitle());
//		catagory.setDescription(catagoryDto.getDescription());
//		
//		Catagory updatedcatagory=this.catagoryRepo.save(catagory);
//		CatagoryDto updatedCatagoryDto=this.catagoryTocatagoryDto(updatedcatagory);
//		return updatedCatagoryDto;
//	}
//
//	@Override
//	public void deleteCatagory(Integer catagoryId) {
//	Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", catagoryId));
//		 this.catagoryRepo.delete(catagory);
//	}
//
//	@Override
//	public List<CatagoryDto> getAllCatagory() {
//		List<Catagory> catagories=this.catagoryRepo.findAll();
//		List<CatagoryDto> dtos=catagories.stream().map(catagory->this.catagoryTocatagoryDto(catagory)).collect(Collectors.toList());
//		return dtos;
//	}
//
//	@Override
//	public CatagoryDto getCatagoryById(Integer id) {
//	Catagory catagory	=this.catagoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Catagory", "Id", id));
//		return catagoryTocatagoryDto(catagory);
//	}
//	public Catagory catagoryDtoToCatagory(CatagoryDto catagoryDto) {
//		Catagory catagory = this.modelMapper.map(catagoryDto, Catagory.class);
//		return catagory;
//	}
//	public CatagoryDto catagoryTocatagoryDto(Catagory catagory) {
//		CatagoryDto catagoryDto=this.modelMapper.map(catagory, CatagoryDto.class);
//		return catagoryDto;
	
}
