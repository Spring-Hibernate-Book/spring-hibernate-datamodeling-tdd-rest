package com.oodd.spring.manytomanyunidirectional.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.entity.User;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class UserDaoImplTest {
	@Autowired
	private UserDao dao ;

	@Test
	public void testInsert() {
		User u1 = new User();
		u1.setName("Alexander Mahone");
		dao.insert(u1);
		
		User u2 = new User();
		u2.setName("Amritendu De");
		dao.insert(u2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		User u1 = new User();
		u1.setName("Alexander Mahone");
		dao.insert(u1);
	
		List<User> pList = dao.getAll();
		User user = pList.get(0);
		
		User u2 = dao.getById(user.getId());
		Assert.assertEquals("Alexander Mahone", u2.getName());
	}
	
	@Test
	public void testDelete() {
		User u1 = new User();
		u1.setName("Alexander Mahone");
		dao.insert(u1);
		
		User u2 = new User();
		u2.setName("Amritendu De");
		dao.insert(u2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(u2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		User u1 = new User();
		u1.setName("Alexander Mahone");
		dao.insert(u1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<User> uList = dao.getAll();
		User user = uList.get(0);
		user.setName("Amritendu De");
		
		dao.update(user);
		
		List<User> uList2 = dao.getAll();
		User user2 = uList2.get(0);
		Assert.assertEquals("Amritendu De", user2.getName());
	}
}
