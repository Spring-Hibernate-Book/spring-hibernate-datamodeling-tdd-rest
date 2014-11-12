package com.oodd.spring.onetomanyselfreference.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyselfreference.dao.CategoryDao;
import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;
import com.oodd.spring.onetomanyselfreference.entity.Category;
import com.oodd.spring.onetomanyselfreference.mapper.CategoryMapper;
import com.oodd.spring.onetomanyselfreference.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao dao;
	@Autowired
	private CategoryMapper mapper;
	
	@Override
	public void create(CategoryDto categoryDto) {
		Category category = mapper.mapDtoToEntity(categoryDto);
		if(null!=categoryDto.getParentId()){
			Category pareCategory = dao.getById(categoryDto.getParentId());
			category.setCategory(pareCategory);
		}
		dao.insert(category);
	}

	@Override
	public CategoryDto findById(Integer id) {
		Category category = dao.getById(id);
		if(null !=category) return mapper.mapEntityToDto(category);
		return null;
	}

	@Override
	public void remove(Integer id) {
		Category category = dao.getById(id);
		if(null != category && null != category.getCategory()){
			category.setCategory(null);
			dao.update(category);
		}
		if(null !=category) dao.delete(category);
	}

	@Override
	public void edit(CategoryDto categoryDto) {
		Category category = mapper.mapDtoToEntity(categoryDto);
		if(null !=categoryDto.getParentId()){
			Category parentCategory = dao.getById(categoryDto.getParentId());
			category.setCategory(parentCategory);
			dao.update(category);
		}
	}

	@Override
	public List<CategoryDto> findAll() {
		List<Category> categories = dao.getAll();
		List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
		if(null !=categories && categories.size() > 0){
			for(Category category : categories){
				categoryDtos.add(mapper.mapEntityToDto(category));
			}
		}
		return categoryDtos;
	}
}
