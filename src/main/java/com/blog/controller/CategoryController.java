package com.blog.controller;

import java.util.List;

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

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// Create 
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categDto)
	{
		CategoryDto createcategory =  this.categoryService.createCategory(categDto);
		return new ResponseEntity<CategoryDto>(createcategory, HttpStatus.CREATED);
	}

	// Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> UpdateCategory(@RequestBody CategoryDto categDto, @PathVariable Integer catId)
	{
		CategoryDto updatecategory =  this.categoryService.updateCategory(categDto , catId);
		return new ResponseEntity<CategoryDto>(updatecategory, HttpStatus.OK);
	}	
			
	// Delete 
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Sucessfully", true), HttpStatus.OK);
	}
	
	
	// Get one
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory( @PathVariable Integer catId)
	{
		CategoryDto categoryDto =  this.categoryService.getCategory(catId);	
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	// Get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getallCategory()
	{
		List<CategoryDto> categoryDto =  this.categoryService.getCategories();	
		return new ResponseEntity<List<CategoryDto>>(categoryDto, HttpStatus.OK);
	}
	
}
