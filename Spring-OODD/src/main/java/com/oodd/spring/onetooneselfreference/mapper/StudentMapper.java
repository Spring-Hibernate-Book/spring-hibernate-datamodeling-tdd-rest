package com.oodd.spring.onetooneselfreference.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.onetooneselfreference.dto.StudentDto;
import com.oodd.spring.onetooneselfreference.entity.Student;
@Component
public class StudentMapper {
	public Student mapDtoToEntity (StudentDto studentDTO){
		Student student = new Student();
		Student mentor = new Student();
		if(null !=studentDTO.getId() && studentDTO.getId()> 0) student.setId(studentDTO.getId());
		if(null !=studentDTO.getName()) student.setName(studentDTO.getName());
		if(null !=studentDTO.getMentorName()){mentor.setName(studentDTO.getMentorName());student.setMentor(mentor);}
		return student;
	}
	public StudentDto mapEntityToDto (Student student){
		StudentDto studentDTO = new StudentDto();
		if(null !=student.getId()) studentDTO.setId(student.getId());
		if(null !=student.getName()) studentDTO.setName(student.getName());
		if(null !=student.getMentor())studentDTO.setMentorName(student.getMentor().getName());
		return studentDTO;
	}
}
