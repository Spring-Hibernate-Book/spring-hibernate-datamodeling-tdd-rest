package com.oodd.spring.onetomanyunidirectional.service;

import java.util.List;

import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;

public interface PersonService {
	public void create(PersonDto personDto) ;
	public List<PersonDto> findAll() ;
	public PersonDto  findById(Integer id) ;
	public void remove(Integer id);
	public void edit(PersonDto personDto) ;
}
