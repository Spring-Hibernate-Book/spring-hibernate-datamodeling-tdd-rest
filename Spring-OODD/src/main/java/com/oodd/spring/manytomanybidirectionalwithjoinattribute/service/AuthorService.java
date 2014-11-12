package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;

public interface AuthorService {
	public void create(AuthorDto authorDto) ;
	public List<AuthorDto> findAll();
	public AuthorDto findById(Integer id);
	public void remove(Integer id);
	public void edit(AuthorDto authorDto);
}
