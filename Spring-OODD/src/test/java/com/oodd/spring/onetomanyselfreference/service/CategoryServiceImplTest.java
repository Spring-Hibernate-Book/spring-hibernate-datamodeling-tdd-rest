package com.oodd.spring.onetomanyselfreference.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyselfreference.dto.CategoryDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class CategoryServiceImplTest {
	@Autowired
	private CategoryService service;
	@Test
	public void testCreate() {
		CategoryDto parent = new CategoryDto();
		parent.setName("Java");
		service.create(parent);
		Assert.assertEquals(1L, service.findAll().size());

		parent = service.findAll().get(0);

		CategoryDto jee = new CategoryDto();
		jee.setName("Java EE");
		jee.setParentId(parent.getId());
		service.create(jee);
		Assert.assertEquals(2L, service.findAll().size());
	}
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		CategoryDto parent = new CategoryDto();
		parent.setName("Java");
		service.create(parent);

		parent = service.findAll().get(0);

		CategoryDto jee = new CategoryDto();
		jee.setName("Java EE");
		jee.setParentId(parent.getId());
		service.create(jee);

		List<CategoryDto> parentDtos = service.findAll();

		CategoryDto dto = service.findById(parentDtos.get(0).getId());
		Assert.assertEquals(dto.getName(),"Java EE");
	}
	@Test
	public void testRemove() {
		CategoryDto parent = new CategoryDto();
		parent.setName("Java");
		service.create(parent);
		Assert.assertEquals(1L, service.findAll().size());

		parent = service.findAll().get(0);

		CategoryDto jee = new CategoryDto();
		jee.setName("Java EE");
		jee.setParentId(parent.getId());
		service.create(jee);
		Assert.assertEquals(2L, service.findAll().size());
		
		List<CategoryDto> parentDtos = service.findAll();
		CategoryDto dto = parentDtos.get(0);
		service.remove(dto.getId());
		Assert.assertEquals(1L, service.findAll().size());
	}
	@Test
	public void testEdit() {
		CategoryDto parent = new CategoryDto();
		parent.setName("Java");
		service.create(parent);
		Assert.assertEquals(1L, service.findAll().size());

		parent = service.findAll().get(0);

		CategoryDto jee = new CategoryDto();
		jee.setName("Java EE");
		jee.setParentId(parent.getId());
		service.create(jee);
		Assert.assertEquals(2L, service.findAll().size());
		
		List<CategoryDto> parentDtos = service.findAll();
		CategoryDto dto = parentDtos.get(0);
		dto.setName("JEE");
		service.edit(dto);
		
		parentDtos = service.findAll();
		dto = parentDtos.get(0);
		Assert.assertEquals("JEE", dto.getName());
	}
}
