package com.oodd.spring.onetomanyselfreference.entity;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class CategoryTest {
	@Autowired
	private SessionFactory sessionFactory;
	@Test
	@SuppressWarnings("unchecked")
	public void testCRUD(){
		Category rootCategory = new Category();
		rootCategory.setName("Java");
		sessionFactory.getCurrentSession().save(rootCategory);

		List<Category> categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();

		Category parentCategory = categories.get(0);
		Category subCategory = new Category();
		subCategory.setName("JEE");
		subCategory.setCategory(parentCategory);
		sessionFactory.getCurrentSession().save(subCategory);

		categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();

		Assert.assertEquals(2L, categories.size());

		rootCategory = categories.get(1);
		subCategory = categories.get(0);
		Assert.assertEquals(rootCategory.getName(),subCategory.getCategory().getName());

		subCategory.setName("Ejb 2.1");
		subCategory.setCategory(null);
		sessionFactory.getCurrentSession().merge(subCategory);

		categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();
		Assert.assertEquals(null, categories.get(0).getCategory());
		
		sessionFactory.getCurrentSession().delete(subCategory);
		categories = sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();
		Assert.assertEquals(1L, categories.size());
	}
}
