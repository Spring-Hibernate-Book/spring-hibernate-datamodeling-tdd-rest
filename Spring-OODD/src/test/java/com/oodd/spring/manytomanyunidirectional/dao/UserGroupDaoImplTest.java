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

import com.oodd.spring.manytomanyunidirectional.entity.Group;
import com.oodd.spring.manytomanyunidirectional.entity.User;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class UserGroupDaoImplTest {

	@Autowired
	private UserDao userDao ;
	
	@Autowired
	private GroupDao groupDao ;
	
	@Autowired
	private UserGroupDao userGroupDao;
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, userGroupDao.getAll().size());
	}
	
	@Test
	public void testIsPresent() {
		boolean status = false;
		
		User u1 = new User();
		u1.setName("Alexander Mahone");
		userDao.insert(u1);
		
		Group g1 = new Group();
		g1.setName("Java User Group");
		groupDao.insert(g1);
		
		List<User> userList = userDao.getAll();		
		User user = userList.get(0);
		
		List<Group> groupList = groupDao.getAll();		
		Group group = groupList.get(0);
		user.getGroups().add(group);
		
		userDao.insert(user);
		
		List<User> userList2 = userGroupDao.isPresent(user.getId(), group.getId());
		if(null != userList2) {			
			if(userList2.size() > 0) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
}
