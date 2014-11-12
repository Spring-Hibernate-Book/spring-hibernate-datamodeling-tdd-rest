package com.oodd.spring.onetooneselfreference.dao;

import java.util.List;

import com.oodd.spring.onetooneselfreference.entity.Student;

public interface StudentDao {
	public void insert(Student student);
	public List <Student> getAll();
	public Student getById(Integer id);
	public void delete(Student student) ;
	public void update(Student student);
}
