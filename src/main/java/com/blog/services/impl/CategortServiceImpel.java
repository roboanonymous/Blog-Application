package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategortServiceImpel implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	
	// Post
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelmapper.map(categoryDto, Category.class);
		Category addedcat = this.categoryRepo.save(cat);
		
		return this.modelmapper.map(addedcat, CategoryDto.class);
		
	}

	// Update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {


		Category cat = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedcat = this.categoryRepo.save(cat);
		
		return this.modelmapper.map(updatedcat, CategoryDto.class);
	}

	
	// Delete
	@Override
	public void deleteCategory(Integer CategoryId) {
		Category cat = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));	
		this.categoryRepo.delete(cat);
	}

	// Get Single 
	@Override
	public CategoryDto getCategory(Integer CategoryId) {
		Category cat = this.categoryRepo.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));	
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	// Get All
	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDto = categories.stream().map((cat) -> this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return catDto;
	}

}
