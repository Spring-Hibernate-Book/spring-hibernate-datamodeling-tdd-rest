package com.oodd.spring.onetooneselfreference.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneselfreference.dao.StudentDao;
import com.oodd.spring.onetooneselfreference.dto.StudentDto;
import com.oodd.spring.onetooneselfreference.entity.Student;
import com.oodd.spring.onetooneselfreference.mapper.StudentMapper;
import com.oodd.spring.onetooneselfreference.service.StudentService;
@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao dao ;
	@Autowired
	private StudentMapper mapper ;
	@Override
	public void create(StudentDto studentDTO) {
		dao.insert(mapper.mapDtoToEntity(studentDTO));
	}

	@Override
	public List<StudentDto> findAll() {
		List<StudentDto> studentDTOs = new ArrayList<StudentDto>();
		List<Student> students = dao.getAll();
		if(null != students){
			for(Student student : students){
				studentDTOs.add(mapper.mapEntityToDto(student));
			}
		}
		return studentDTOs;
	}

	@Override
	public StudentDto findById(Integer id) {
		Student student = dao.getById(id);
		if(null !=student){
			return mapper.mapEntityToDto(student);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Student student = dao.getById(id);
		if(null != student)
			dao.delete(student);
	}

	@Override
	public void edit(StudentDto studentDTO) {
			dao.update(mapper.mapDtoToEntity(studentDTO));
	}

}
