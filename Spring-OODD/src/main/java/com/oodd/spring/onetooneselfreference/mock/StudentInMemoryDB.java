package com.oodd.spring.onetooneselfreference.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.onetooneselfreference.dto.StudentDto;

public enum StudentInMemoryDB {
	
	INSTANCE;
	private static Integer lastId = 0;
	private static List<StudentDto> list = new ArrayList<StudentDto>();
	
	public Integer getId() {return ++lastId;}
	
	public void add(StudentDto studentDto){
		studentDto.setId(getId());
		list.add(studentDto);
	}
	
	public void edit(StudentDto studentDto){
		for(StudentDto dto : list){
			if(studentDto.getId() == dto.getId()){
				dto.setName(studentDto.getName());
				dto.setMentorName(studentDto.getMentorName()); 
				break;
			}
		}
	}
	
	public void remove(Integer id) {
		StudentDto dto = null;
		for(StudentDto studentDto : list){
			if(studentDto.getId() ==  id) {
				dto = studentDto; 
				break;
			}
		}
		if(null != dto) list.remove(dto);
	}
	
	public List<StudentDto> findAll(){return list;}
	
	public StudentDto findById(Integer id) {
		for(StudentDto studentDto : list){ 
			if(studentDto.getId() == id) 
				return studentDto;
		}
		return null ;
	}
}
