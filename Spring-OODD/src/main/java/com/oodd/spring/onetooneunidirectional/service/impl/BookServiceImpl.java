package com.oodd.spring.onetooneunidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneunidirectional.dao.BookDao;
import com.oodd.spring.onetooneunidirectional.dto.BookDto;
import com.oodd.spring.onetooneunidirectional.entity.Book;
import com.oodd.spring.onetooneunidirectional.mapper.BookMapper;
import com.oodd.spring.onetooneunidirectional.service.BookService;
@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao dao;
	@Autowired
	private BookMapper mapper;
	@Override
	public void create(BookDto bookDto) {
		dao.insert(mapper.mapDtoToEntity(bookDto));
	}

	@Override
	public List<BookDto> findAll() {
		List<Book> books = dao.getAll();
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		for(Book book : books){
			bookDtos.add(mapper.mapEntityToDto(book));
		}
		return bookDtos;
	}

	@Override
	public BookDto findById(int id) {
		Book book = dao.getById(id);
		if(null !=book){
			return mapper.mapEntityToDto(book);
		}
		return null;
	}

	@Override
	public void remove(int id) {
		Book book = dao.getById(id);
		dao.delete(book);
	}

	@Override
	public void edit(BookDto bookDto) {
		dao.update(mapper.mapDtoToEntity(bookDto));
	}

}
