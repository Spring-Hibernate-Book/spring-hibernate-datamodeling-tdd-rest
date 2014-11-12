/**
 * 
 */
package com.oodd.spring.standalone.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.oodd.spring.standalone.dto.ProductDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ProductServiceImplTest {
	@Autowired
	private ProductService service;
	
	@Test
	public void testCreate() {
		ProductDto sproduct = new ProductDto();
		sproduct.setName("Spring in Action Book");
		service.create(sproduct);
		
		ProductDto hProduct = new ProductDto();
		hProduct.setName("Hibernate in Action Book");
		service.create(hProduct);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		ProductDto sproduct = new ProductDto();
		sproduct.setName("Spring in Action Book");
		service.create(sproduct);
	
		List<ProductDto> pList = service.findAll();
		ProductDto product = pList.get(0);
		
		ProductDto product2 = service.findById(product.getId());
		Assert.assertEquals("Spring in Action Book", product2.getName());
	}
	
	@Test
	public void testRemove() {
		ProductDto sproduct = new ProductDto();
		sproduct.setName("Spring in Action Book");
		service.create(sproduct);
		
		ProductDto hProduct = new ProductDto();
		hProduct.setName("Hibernate in Action Book");
		service.create(hProduct);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<ProductDto> pList = service.findAll();
		ProductDto product = pList.get(0);
		service.remove(product.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		ProductDto sproduct = new ProductDto();
		sproduct.setName("Spring in Action Book");
		service.create(sproduct);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<ProductDto> pList = service.findAll();
		ProductDto product = pList.get(0);
		product.setName("Head First Design Patterns");
		
		service.edit(product);
		
		List<ProductDto> pList2 = service.findAll();
		ProductDto product2 = pList2.get(0);
		Assert.assertEquals("Head First Design Patterns", product2.getName());
	}
	
}
