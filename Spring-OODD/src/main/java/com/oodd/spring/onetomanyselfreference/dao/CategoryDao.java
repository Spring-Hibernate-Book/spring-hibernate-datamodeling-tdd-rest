package com.oodd.spring.onetomanyselfreference.dao;

import java.util.List;

import com.oodd.spring.onetomanyselfreference.entity.Category;

public interface CategoryDao {
	public void insert(Category category);
	public Category getById(Integer id);
	public void delete(Category category);
	public void update(Category category);
	public List<Category> getAll();
}
