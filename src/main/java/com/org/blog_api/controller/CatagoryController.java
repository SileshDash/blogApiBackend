package com.org.blog_api.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.blog_api.payload.CatagoryDto;
import com.org.blog_api.service.catagoryService;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/catagory")
public class CatagoryController {
	@Autowired
	private catagoryService catagoryService;
	
	@PostMapping("/save")
	private ResponseEntity<CatagoryDto> saveCatagory(@Valid @RequestBody CatagoryDto catagoryDto) {
	CatagoryDto saveCatagory=catagoryService.saveCatagory(catagoryDto);
	return new ResponseEntity<CatagoryDto>(saveCatagory,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	private ResponseEntity<List<CatagoryDto>> getAllCatagory(){
		
		return ResponseEntity.ok(catagoryService.getAllCatagory());
	}
	
	@GetMapping("/get/{catagoryId}")
	private ResponseEntity<CatagoryDto>getCatagoryByid(@Nullable@PathVariable Integer catagoryId){
		
		return ResponseEntity.ok(this.catagoryService.getCatagoryById(catagoryId));
	}
	@PutMapping("/update/{catagoryId}")
	private ResponseEntity<CatagoryDto>updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto,@PathVariable Integer catagoryId){
		CatagoryDto updatedCatagory=this.catagoryService.updateCatagory(catagoryDto, catagoryId);
		return ResponseEntity.ok(updatedCatagory);
	}
	@DeleteMapping("/delete/{catagoryId}")
	private ResponseEntity<?> deleteCatagory( @PathVariable Integer catagoryId) {
		this.catagoryService.deleteCatagory(catagoryId);
		return new ResponseEntity(Map.of("Message", "catagory deleted Sucessfully"),HttpStatus.OK);
	}
}
