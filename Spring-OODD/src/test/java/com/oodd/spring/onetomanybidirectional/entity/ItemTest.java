package com.oodd.spring.onetomanybidirectional.entity;

import java.util.ArrayList;
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
public class ItemTest {
	@Autowired
	private SessionFactory sessionFactory;
	@Test
	public void testCRUD(){
		Item item1 = new Item();
		item1.setName("Book");
		List<Feature> list = new ArrayList<Feature>();
		Feature feature1 = new Feature();
		feature1.setName("Core Java");
		feature1.setItem(item1);
		Feature feature2 = new Feature();
		feature2.setName("Spring");
		feature2.setItem(item1);
		list.add(feature1);
		list.add(feature2);
		item1.setFeatures(list);
		
		sessionFactory.getCurrentSession().save(item1);
		
		Item item2 = new Item();
		item2.setName("Bags");
		List<Feature> list2 = new ArrayList<Feature>();
		Feature feature3 = new Feature();
		feature3.setName("SkyBags");
		feature3.setItem(item2);
		Feature feature4 = new Feature();
		feature4.setName("WildCraft");
		feature4.setItem(item2);
		list2.add(feature3);
		list2.add(feature4);
		item2.setFeatures(list2);
		
		sessionFactory.getCurrentSession().save(item2);
		
		@SuppressWarnings("unchecked")
		List<Item> items = sessionFactory.getCurrentSession()
				.createQuery("select item from Item item order by item.id desc").list();
		Assert.assertEquals(2L,items.size());
		
		Item item = items.get(0);
		item.setName("BookList");
		sessionFactory.getCurrentSession().update(item);
		
		@SuppressWarnings("unchecked")
		List<Item> itemslist = sessionFactory.getCurrentSession()
				.createQuery("select item from Item item order by item.id desc").list();
		Item pItem = itemslist.get(0);
		Assert.assertEquals("BookList",pItem.getName());
		
		sessionFactory.getCurrentSession().delete(pItem);
		
		@SuppressWarnings("unchecked")
		List<Item> ppItem =  sessionFactory.getCurrentSession()
				.createQuery("select item from Item item order by item.id desc").list();
		Assert.assertEquals(1L,ppItem.size());
	}
}
