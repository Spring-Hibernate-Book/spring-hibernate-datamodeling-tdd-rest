package com.oodd.spring.onetooneunidirectional.dao;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneunidirectional.entity.Book;
import com.oodd.spring.onetooneunidirectional.entity.Shipping;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class BookDaoImplTest {

	@Autowired
	private BookDao dao;

	@Test
	public void testInsert(){
		Book book1 = new Book();
		book1.setName("Java Book");
		Shipping shipping1 = new Shipping();
		shipping1.setCity("UK");
		book1.setShipping(shipping1);
		dao.insert(book1);

		Book book2 = new Book();
		book2.setName("Java SE Book");
		Shipping shipping2 = new Shipping();
		shipping2.setCity("IN");
		book2.setShipping(shipping2);
		dao.insert(book2);

		Assert.assertEquals(2L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
		Book book1 = new Book();
		book1.setName("Java Book");
		Shipping shipping1 = new Shipping();
		shipping1.setCity("UK");
		book1.setShipping(shipping1);
		dao.insert(book1);
		
		List<Book> books = dao.getAll();
		Book book = books.get(0);
		
		Book tempbook = dao.getById(book.getId());
		Assert.assertEquals(tempbook.getName(),book.getName());
		
	}
	@Test
	public void testDelete() {
		Book book1 = new Book();
		book1.setName("Java Book");
		Shipping shipping1 = new Shipping();
		shipping1.setCity("UK");
		book1.setShipping(shipping1);
		dao.insert(book1);

		Book book2 = new Book();
		book2.setName("Java SE Book");
		Shipping shipping2 = new Shipping();
		shipping2.setCity("IN");
		book2.setShipping(shipping2);
		dao.insert(book2);

		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(book2);
		Assert.assertEquals(1L, dao.getAll().size());
		
	}
	@Test
	public void testUpdate() {
		Book book1 = new Book();
		book1.setName("Java Book");
		Shipping shipping1 = new Shipping();
		shipping1.setCity("UK");
		book1.setShipping(shipping1);
		dao.insert(book1);
		
		Assert.assertEquals(1L, dao.getAll().size());
		
		List<Book> books = dao.getAll();
		Book book2 = books.get(0);
		
		Shipping shipping = book2.getShipping();
		shipping.setCity("IND");
		dao.update(book2);
		
		List<Book> books1 = dao.getAll();
		Book book3 = books1.get(0);
		Assert.assertEquals("IND", book3.getShipping().getCity());
	}
}
