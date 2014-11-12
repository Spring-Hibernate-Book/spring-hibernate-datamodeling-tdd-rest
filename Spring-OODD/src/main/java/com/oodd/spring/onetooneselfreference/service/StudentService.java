package com.oodd.spring.onetooneselfreference.service;

import java.util.List;

import com.oodd.spring.onetooneselfreference.dto.StudentDto;

public interface StudentService {
	public void create(StudentDto studentDTO);
	public List<StudentDto> findAll();
	public StudentDto findById(Integer id) ;
	public void remove(Integer id);
	public void edit(StudentDto studentDTO);
}
