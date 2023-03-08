package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {
	
	// create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId);
	
	// Delete
	public void deleteCategory(Integer CategoryId);
	
	//get
	CategoryDto getCategory(Integer CategoryId);
	
	//get All
	List<CategoryDto> getCategories();

}
