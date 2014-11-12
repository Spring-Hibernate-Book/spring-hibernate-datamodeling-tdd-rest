package com.oodd.spring.onetomanyselfreference.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;
import com.oodd.spring.onetomanyselfreference.entity.Category;

@Component
public class CategoryMapper {
	public Category  mapDtoToEntity(CategoryDto categoryDto){
		Category category = new Category();
		if(null !=categoryDto.getId()) category.setId(categoryDto.getId());
		if(null !=categoryDto.getName()) category.setName(categoryDto.getName());
		return category;
	}
	public CategoryDto mapEntityToDto(Category category){
		CategoryDto categoryDto = new CategoryDto();
		if(null !=category.getId()) categoryDto.setId(category.getId());
		if(null !=category.getName()) categoryDto.setName(category.getName());
		if(null !=category.getCategory()) categoryDto.setParentId(category.getCategory().getId());
		return categoryDto;
	}
}
