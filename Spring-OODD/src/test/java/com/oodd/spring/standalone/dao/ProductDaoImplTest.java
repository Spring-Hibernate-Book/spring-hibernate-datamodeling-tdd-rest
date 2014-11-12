package com.oodd.spring.standalone.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.oodd.spring.standalone.dao.ProductDao;
import com.oodd.spring.standalone.entity.Product;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ProductDaoImplTest {
	@Autowired
	private ProductDao dao ;

	@Test
	public void testInsert() {
		Product sproduct = new Product();
		sproduct.setName("Spring in Action Book");
		dao.insert(sproduct);
		
		Product hProduct = new Product();
		hProduct.setName("Hibernate in Action Book");
		dao.insert(hProduct);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Product sproduct = new Product();
		sproduct.setName("Spring in Action Book");
		dao.insert(sproduct);
	
		List<Product> pList = dao.getAll();
		Product product = pList.get(0);
		
		Product product2 = dao.getById(product.getId());
		Assert.assertEquals("Spring in Action Book", product2.getName());
	}
	
	@Test
	public void testDelete() {
		Product sproduct = new Product();
		sproduct.setName("Spring in Action Book");
		dao.insert(sproduct);
		
		Product hProduct = new Product();
		hProduct.setName("Hibernate in Action Book");
		dao.insert(hProduct);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(hProduct);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Product sproduct = new Product();
		sproduct.setName("Spring in Action Book");
		dao.insert(sproduct);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Product> pList = dao.getAll();
		Product product = pList.get(0);
		product.setName("Head First Design Patterns");
		
		dao.update(product);
		
		List<Product> pList2 = dao.getAll();
		Product product2 = pList2.get(0);
		Assert.assertEquals("Head First Design Patterns", product2.getName());
	}
}
