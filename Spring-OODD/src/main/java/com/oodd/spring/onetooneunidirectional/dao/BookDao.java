package com.oodd.spring.onetooneunidirectional.dao;

import java.util.List;

import com.oodd.spring.onetooneunidirectional.entity.Book;
public interface BookDao {
	public void insert( Book book ) ;
	public List<Book> getAll();
	public Book getById(int id);
	public void delete(Book book) ;
	public void update(Book book);
}
