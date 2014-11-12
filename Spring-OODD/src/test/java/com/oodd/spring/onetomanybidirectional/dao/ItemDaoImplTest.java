package com.oodd.spring.onetomanybidirectional.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanybidirectional.entity.Feature;
import com.oodd.spring.onetomanybidirectional.entity.Item;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ItemDaoImplTest {
	@Autowired
	private ItemDao dao;
	@Test
	public void testInsert(){
		Item item = new Item();
		item.setName("Book");
		List<Feature> list = new ArrayList<Feature>();
		Feature feature1 = new Feature();
		feature1.setName("Core Java");
		feature1.setItem(item);
		Feature feature2 = new Feature();
		feature2.setName("Spring");
		feature2.setItem(item);
		list.add(feature1);
		list.add(feature2);
		item.setFeatures(list);
		dao.insert(item);
		Assert.assertEquals(1L, dao.getAll().size());
	}
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	@Test
	public void testGetById() {
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
		dao.insert(item1);
		
		Assert.assertEquals(1L, dao.getAll().size());
		
		List<Item> items = dao.getAll();
		Item item2 = items.get(0);
		Item item3 = dao.getById(item2.getId());
		
		Assert.assertEquals("Book", item3.getName());
		Assert.assertEquals(2L, item3.getFeatures().size());
	}
	@Test
	public void testDelete() {
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
		dao.insert(item1);
		
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
		dao.insert(item2);
		Assert.assertEquals(2L, dao.getAll().size());
		
		List<Item> items = dao.getAll();
		Item item = items.get(0);
		dao.delete(item);
		Assert.assertEquals(1L, dao.getAll().size());
	}
	@Test
	public void testUpdate() {
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
		dao.insert(item1);
		
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
		dao.insert(item2);
		
		List<Item> items = dao.getAll();
		Item item = items.get(1);
		Feature feature = item.getFeatures().get(1);
		feature.setName("Spring 3.2");
		dao.update(item);
		items = dao.getAll();
		item = items.get(1);
		Assert.assertEquals("Spring 3.2",item.getFeatures().get(1).getName());
	}
}
