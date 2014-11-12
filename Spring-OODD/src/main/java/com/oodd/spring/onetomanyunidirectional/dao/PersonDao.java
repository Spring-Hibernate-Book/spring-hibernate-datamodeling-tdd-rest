package com.oodd.spring.onetomanyunidirectional.dao;

import java.util.List;

import com.oodd.spring.onetomanyunidirectional.entity.Person;

public interface PersonDao {
	public void insert(Person person);
	public List <Person>  getAll();
	public Person getById(Integer id);
	public void delete(Person person) ;
	public void update(Person person);
}
