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
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ManuscriptAuthorServiceImplTest {

	@Autowired
	private ManuscriptService manuscriptService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ManuscriptAuthorService manuscriptAuthorService;
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, manuscriptAuthorService.findAll().size());
	}

	@Test
	public void testCreate() {
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
		
		manuscriptAuthorService.create(manuscriptAuthorDto);
		Assert.assertEquals(1L, manuscriptAuthorService.findAll().size());
	}
	
	@Test
	public void testIsPresent() {	
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
		
		manuscriptAuthorService.create(manuscriptAuthorDto);
		Assert.assertEquals(1L, manuscriptAuthorService.findAll().size());		
		
		boolean status = manuscriptAuthorService.isPresent(manuscriptAuthorDto);		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testRemove() {	
		ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();
		
		ManuscriptDto manuscriptDto = new ManuscriptDto();
		manuscriptDto.setName("The Immortals of Meluha");
		manuscriptService.create(manuscriptDto);
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Amish Tripathi");
		authorService.create(authorDto);
		
		List<ManuscriptDto> manuscriptDtos = manuscriptService.findAll();
		ManuscriptDto manuscriptDto1 = manuscriptDtos.get(0);
		
		List<AuthorDto> authorDtos = authorService.findAll();
		AuthorDto authorDto1 = authorDtos.get(0);
		
		manuscriptAuthorDto.setManuscriptDto(manuscriptDto1);
		manuscriptAuthorDto.setAuthorDto(authorDto1);
		manuscriptAuthorDto.setPublisher("Createspace");
		
		manuscriptAuthorService.create(manuscriptAuthorDto);
		Assert.assertEquals(1L, manuscriptAuthorService.findAll().size());
		
		List<ManuscriptAuthorDto> manuscriptAuthorList = manuscriptAuthorService.findAll();
		ManuscriptAuthorDto manuscriptAuthorDto1 = manuscriptAuthorList.get(0);
		manuscriptAuthorService.remove(manuscriptAuthorDto1);
		
		Assert.assertEquals(0, manuscriptAuthorService.findAll().size());
	}
}
