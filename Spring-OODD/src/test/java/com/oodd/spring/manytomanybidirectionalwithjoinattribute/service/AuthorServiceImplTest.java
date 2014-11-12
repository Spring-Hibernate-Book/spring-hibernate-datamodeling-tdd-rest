package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class AuthorServiceImplTest {

	@Autowired
	private AuthorService authorService;
	
	@Test
	public void testCreate() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amritendu De");
		authorService.create(authorDto);
		
		AuthorDto authorDto2 = new AuthorDto();
		authorDto2.setName("Amish Tripathi");
		authorService.create(authorDto2);
	
		Assert.assertEquals(2L, authorService.findAll().size());
	}
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, authorService.findAll().size());
	}
	
	@Test
	public void testFindById() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amritendu De");
		authorService.create(authorDto);
	
		List<AuthorDto> mList = authorService.findAll();
		AuthorDto authorDto2 = mList.get(0);
		
		AuthorDto authorDto3 = authorService.findById(authorDto2.getId());
		Assert.assertEquals("Amritendu De", authorDto3.getName());
	}
	
	@Test
	public void testRemove() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amritendu De");
		authorService.create(authorDto);
			
		AuthorDto authorDto2 = new AuthorDto();
		authorDto2.setName("Amish Tripathi");
		authorService.create(authorDto2);
	
		Assert.assertEquals(2L, authorService.findAll().size());
		
		List<AuthorDto> mList = authorService.findAll();
		AuthorDto authorDto3 = mList.get(0);
		authorService.remove(authorDto3.getId());
		
		Assert.assertEquals(1L, authorService.findAll().size());
	}
	
	@Test
	public void testEdit() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amritendu De");
		authorService.create(authorDto);
		
		Assert.assertEquals(1L, authorService.findAll().size());
		
		List<AuthorDto> mList = authorService.findAll();
		AuthorDto authorDto2 = mList.get(0);
		authorDto2.setName("Amish Tripathi");
		
		authorService.edit(authorDto2);
		
		List<AuthorDto> mList2 = authorService.findAll();
		AuthorDto authorDto3 = mList2.get(0);
		Assert.assertEquals("Amish Tripathi", authorDto3.getName());
	}
}