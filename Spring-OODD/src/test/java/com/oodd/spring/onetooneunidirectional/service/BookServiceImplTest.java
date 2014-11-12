package com.oodd.spring.onetooneunidirectional.service;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneunidirectional.dto.BookDto;
import com.oodd.spring.standalone.dto.ProductDto;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class BookServiceImplTest {
	@Autowired
	private BookService service ;
	@Test
	public void testCreate() {
		BookDto bookDto = new BookDto();
		bookDto.setName("Java SE");
		bookDto.setCity("IND");
		service.create(bookDto);
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		BookDto bookDto = new BookDto();
		bookDto.setName("Java SE");
		bookDto.setCity("IND");
		service.create(bookDto);
		
		List<BookDto> bookDtos = service.findAll();
		BookDto bDto = bookDtos.get(0);
		
		BookDto bDto2 = service.findById(bDto.getId());
		Assert.assertEquals("Java SE", bDto2.getName());
		Assert.assertEquals("IND", bDto2.getCity());
	}
	@Test
	public void testRemove() {
		BookDto bookDto = new BookDto();
		bookDto.setName("Java SE");
		bookDto.setCity("IND");
		service.create(bookDto);
		
		Assert.assertEquals(1L, service.findAll().size());
		List<BookDto> bookDtos = service.findAll();
		BookDto bDto = bookDtos.get(0);
		
		service.remove(bDto.getId());
		Assert.assertEquals(0L, service.findAll().size());		
	}
	@Test
	public void testEdit() {
		BookDto bookDto = new BookDto();
		bookDto.setName("Java SE");
		bookDto.setCity("IND");
		service.create(bookDto);
		
		Assert.assertEquals(1L, service.findAll().size());
		
		List<BookDto> bookDtos2 = service.findAll();
		BookDto bDto2 = bookDtos2.get(0);
		bDto2.setCity("CAN");
		
		service.edit(bDto2);
		
		List<BookDto> bookDtos = service.findAll();
		BookDto bDto = bookDtos.get(0);
		
		Assert.assertEquals("CAN", bDto.getCity());
	}
}
