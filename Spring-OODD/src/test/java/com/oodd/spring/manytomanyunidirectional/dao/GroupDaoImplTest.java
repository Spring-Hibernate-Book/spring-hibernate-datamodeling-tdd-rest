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

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class GroupDaoImplTest {
	@Autowired
	private GroupDao dao ;

	@Test
	public void testInsert() {
		Group g1 = new Group();
		g1.setName("Java User Group");
		dao.insert(g1);
		
		Group g2 = new Group();
		g2.setName("Microsoft Technology Group");
		dao.insert(g2);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Group g1 = new Group();
		g1.setName("Java User Group");
		dao.insert(g1);
	
		List<Group> gList = dao.getAll();
		Group group = gList.get(0);
		
		Group u2 = dao.getById(group.getId());
		Assert.assertEquals("Java User Group", u2.getName());
	}
	
	@Test
	public void testDelete() {
		Group g1 = new Group();
		g1.setName("Java User Group");
		dao.insert(g1);
		
		Group g2 = new Group();
		g2.setName("Microsoft Technology Group");
		dao.insert(g2);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(g2);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Group g1 = new Group();
		g1.setName("Java User Group");
		dao.insert(g1);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Group> gList = dao.getAll();
		Group group = gList.get(0);
		group.setName("Microsoft Technology Group");
		
		dao.update(group);
		
		List<Group> gList2 = dao.getAll();
		Group group2 = gList2.get(0);
		Assert.assertEquals("Microsoft Technology Group", group2.getName());
	}
}
