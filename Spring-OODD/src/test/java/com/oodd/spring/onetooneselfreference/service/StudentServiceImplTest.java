package com.oodd.spring.onetooneselfreference.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneselfreference.dto.StudentDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class StudentServiceImplTest {
	@Autowired
	private StudentService service ;
	@Test
	public void testCreate() {
		StudentDto studentDTO = new StudentDto();
		studentDTO.setName("Alex");
		studentDTO.setMentorName("Fred");
		service.create(studentDTO);
		Assert.assertEquals(2L, service.findAll().size());
	}
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		StudentDto studentDTO = new StudentDto();
		studentDTO.setName("Alex");
		studentDTO.setMentorName("Fred");
		service.create(studentDTO);
		List<StudentDto> studentDTOs = service.findAll();
		StudentDto stDto = studentDTOs.get(1) ;
		StudentDto stDto2 = service.findById(stDto.getId());
		Assert.assertNotNull(stDto2);
	}
	@Test
	public void testRemove() {
		StudentDto studentDTO = new StudentDto();
		studentDTO.setName("Alex");
		studentDTO.setMentorName("Fred");
		service.create(studentDTO);
		
		StudentDto stuDto = new StudentDto();
		stuDto.setName("Smith");
		stuDto.setMentorName("Flex");
		service.create(stuDto);
		Assert.assertEquals(4L, service.findAll().size());
		
		List<StudentDto> studentDTOs = service.findAll();
		StudentDto stDto = studentDTOs.get(3) ;
		service.remove(stDto.getId());
		Assert.assertEquals(2L, service.findAll().size());
		
	}
	@Test
	public void testEdit() {
		StudentDto studentDTO = new StudentDto();
		studentDTO.setName("Alex");
		studentDTO.setMentorName("Fred");
		service.create(studentDTO);
		List<StudentDto> studentDTOs = service.findAll();
		StudentDto sDto = studentDTOs.get(1);
		sDto.setName("James Alex");
		sDto.setMentorName("Fred James");
		service.edit(sDto);
		List<StudentDto> students = service.findAll();
		Assert.assertEquals(2L, students.size());
	}
}
