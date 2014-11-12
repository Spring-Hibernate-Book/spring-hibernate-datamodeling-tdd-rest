package com.oodd.spring.onetooneunidirectional.service;

import java.util.List;

import com.oodd.spring.onetooneunidirectional.dto.BookDto;
public interface BookService {
	public void create(BookDto bookDto ) ;
	public List<BookDto> findAll();
	public BookDto findById(int id);
	public void remove(int productId);
	public void edit(BookDto bookDto);
}
