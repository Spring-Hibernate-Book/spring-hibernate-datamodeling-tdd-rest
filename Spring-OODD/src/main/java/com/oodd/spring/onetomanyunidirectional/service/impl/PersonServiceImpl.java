package com.oodd.spring.onetomanyunidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyunidirectional.dao.PersonDao;
import com.oodd.spring.onetomanyunidirectional.dto.PersonDto;
import com.oodd.spring.onetomanyunidirectional.entity.Person;
import com.oodd.spring.onetomanyunidirectional.mapper.PersonMapper;
import com.oodd.spring.onetomanyunidirectional.service.PersonService;
@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDao dao;
	@Autowired
	private PersonMapper mapper;
	@Override
	public void create(PersonDto personDto) {
		dao.insert(mapper.mapDtoToEntity(personDto));
	}

	@Override
	public List<PersonDto> findAll() {
		List<Person> persons = dao.getAll();
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		if(null !=persons){
			for(Person person : persons){personDtos.add(mapper.mapEntityToDto(person));}
		}
		return personDtos;
	}

	@Override
	public PersonDto findById(Integer id) {
		Person person = dao.getById(id);
		if(null !=person){return mapper.mapEntityToDto(person);}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Person person = dao.getById(id);
		if(null !=person){dao.delete(person);}
	}

	@Override
	public void edit(PersonDto personDto) {
		dao.update(mapper.mapDtoToEntity(personDto));
	}

}
