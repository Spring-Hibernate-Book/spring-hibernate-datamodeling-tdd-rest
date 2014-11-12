package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;

public interface AuthorDao {
	public void insert(Author author);
	public List <Author>  getAll();
	public Author getById(Integer id);
	public void delete(Author author) ;
	public void update(Author author);
}
