package com.oodd.spring.onetomanyselfreference.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyselfreference.entity.Category;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class CategoryDaoImplTest {
	@Autowired
	private CategoryDao dao ;
	@Test
	public void testInsert(){
		Category rootCategory = new Category();
		rootCategory.setName("Java");
		dao.insert(rootCategory);
		Assert.assertEquals(1L, dao.getAll().size());

		List<Category> categories = dao.getAll();
		Category parentCategory = categories.get(0);
		Category subCategory = new Category();
		subCategory.setName("JEE");
		subCategory.setCategory(parentCategory);
		dao.insert(subCategory);
		Assert.assertEquals(2L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
		Category rootCategory = new Category();
		rootCategory.setName("Java");
		dao.insert(rootCategory);

		List<Category> categories = dao.getAll();
		Category parentCategory = categories.get(0);
		Category subCategory = new Category();
		subCategory.setName("JEE");
		subCategory.setCategory(parentCategory);
		dao.insert(subCategory);

		categories = dao.getAll();

		Category searchCategory = dao.getById(categories.get(1).getId());
		Assert.assertEquals("Java", searchCategory.getName());
	}
	
	@Test
	public void testDelete() {
		Category rootCategory = new Category();
		rootCategory.setName("Java");
		dao.insert(rootCategory);

		List<Category> categories = dao.getAll();
		Category parentCategory = categories.get(0);
		Category subCategory = new Category();
		subCategory.setName("JEE");
		subCategory.setCategory(parentCategory);
		dao.insert(subCategory);
		Assert.assertEquals(2L, dao.getAll().size());

		categories = dao.getAll();
		Category searchCategory = categories.get(0);
		searchCategory.setCategory(null);
		dao.update(searchCategory);
		dao.delete(searchCategory);
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Category rootCategory = new Category();
		rootCategory.setName("Java");
		dao.insert(rootCategory);

		Category nextCategory = new Category();
		nextCategory.setName("Ejb 2.1");

		List<Category> categories = dao.getAll();
		rootCategory = categories.get(0);
		nextCategory.setCategory(rootCategory);
		dao.insert(nextCategory);
		
		categories = dao.getAll();
		Category parentCategory = categories.get(0);
		Category subCategory = new Category();
		subCategory.setName("JEE");
		subCategory.setCategory(parentCategory);
		dao.insert(subCategory);

		categories = dao.getAll();
		Category searchCategory = categories.get(0);
		searchCategory.setName("Spring 3.0");
		rootCategory = categories.get(2);
		searchCategory.setCategory(rootCategory);
		dao.update(searchCategory);
		categories = dao.getAll();
		
		Assert.assertEquals("Java", categories.get(0).getCategory().getName());
	}
}
