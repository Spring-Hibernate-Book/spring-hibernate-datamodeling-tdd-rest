package com.oodd.spring.concretetableinheritance.entity;

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

import com.oodd.spring.concretetableinheritance.entity.Building;
import com.oodd.spring.concretetableinheritance.entity.Estate;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class BuildingTest {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD()
	{
		Estate p1 = new Estate();
		p1.setName("Estate");		
		sessionFactory.getCurrentSession().save(p1);
		
		Building building = new Building();
		building.setName("Building");
		building.setFloors(20);
		sessionFactory.getCurrentSession().save(building);
		
		building.setName("Building Estate");
		building.setFloors(30);
		sessionFactory.getCurrentSession().merge(building);

		List<Building> list = sessionFactory.getCurrentSession().createQuery("from Building").list();
		Assert.assertEquals(1L, list.size());
		
		sessionFactory.getCurrentSession().delete(building);
		
		List<Building> list2 = sessionFactory.getCurrentSession().createQuery("from Building").list();
		Assert.assertEquals(0L, list2.size());
	}
}
