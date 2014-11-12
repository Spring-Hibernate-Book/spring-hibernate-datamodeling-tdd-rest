package com.oodd.spring.onetomanyselfreference.service;

import java.util.List;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;

public interface CategoryService {
	public void create(CategoryDto categoryDto);
	public CategoryDto findById(Integer id);
	public void remove(Integer id);
	public void edit(CategoryDto categoryDto);
	public List<CategoryDto> findAll();
}
