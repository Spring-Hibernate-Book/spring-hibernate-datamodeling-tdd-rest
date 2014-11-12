package com.oodd.spring.concretetableinheritance.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.concretetableinheritance.dao.EstateDao;
import com.oodd.spring.concretetableinheritance.entity.Estate;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class EstateDaoImplTest {
	@Autowired
	private EstateDao dao ;

	@Test
	public void testInsert() {
		Estate testate = new Estate();
		testate.setName("Royal Estate");
		dao.insert(testate);
		
		Estate sEstate = new Estate();
		sEstate.setName("Majestic Estate");
		dao.insert(sEstate);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Estate sestate = new Estate();
		sestate.setName("Majestic Estate");
		dao.insert(sestate);
	
		List<Estate> pList = dao.getAll();
		Estate estate = pList.get(0);
		
		Estate estate2 = dao.getById(estate.getId());
		Assert.assertEquals("Majestic Estate", estate2.getName());
	}
	
	@Test
	public void testDelete() {
		Estate sestate = new Estate();
		sestate.setName("Majestic Estate");
		dao.insert(sestate);
		
		Estate tEstate = new Estate();
		tEstate.setName("Royal Estate");
		dao.insert(tEstate);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(tEstate);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Estate sestate = new Estate();
		sestate.setName("Majestic Estate");
		dao.insert(sestate);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Estate> pList = dao.getAll();
		Estate estate = pList.get(0);
		estate.setName("Royal Estate");
		
		dao.update(estate);
		
		List<Estate> pList2 = dao.getAll();
		Estate estate2 = pList2.get(0);
		Assert.assertEquals("Royal Estate", estate2.getName());
	}
}
