package com.oodd.spring.singletableinheritance.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.singletableinheritance.entity.Protocol;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ProtocolDaoImplTest {
	@Autowired
	private ProtocolDao dao ;

	@Test
	public void testInsert() {
		Protocol tprotocol = new Protocol();
		tprotocol.setName("TCP/IP");
		dao.insert(tprotocol);
		
		Protocol sProtocol = new Protocol();
		sProtocol.setName("SNMP");
		dao.insert(sProtocol);
	
		Assert.assertEquals(2L, dao.getAll().size());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(0L, dao.getAll().size());
	}
	
	@Test
	public void testGetById() {
		Protocol sprotocol = new Protocol();
		sprotocol.setName("SNMP");
		dao.insert(sprotocol);
	
		List<Protocol> pList = dao.getAll();
		Protocol protocol = pList.get(0);
		
		Protocol protocol2 = dao.getById(protocol.getId());
		Assert.assertEquals("SNMP", protocol2.getName());
	}
	
	@Test
	public void testDelete() {
		Protocol sprotocol = new Protocol();
		sprotocol.setName("SNMP");
		dao.insert(sprotocol);
		
		Protocol tProtocol = new Protocol();
		tProtocol.setName("TCP/IP");
		dao.insert(tProtocol);
	
		Assert.assertEquals(2L, dao.getAll().size());
		
		dao.delete(tProtocol);
		
		Assert.assertEquals(1L, dao.getAll().size());
	}
	
	@Test
	public void testUpdate() {
		Protocol sprotocol = new Protocol();
		sprotocol.setName("SNMP");
		dao.insert(sprotocol);
		
		Assert.assertEquals(1L, dao.getAll().size());

		List<Protocol> pList = dao.getAll();
		Protocol protocol = pList.get(0);
		protocol.setName("TCP/IP");
		
		dao.update(protocol);
		
		List<Protocol> pList2 = dao.getAll();
		Protocol protocol2 = pList2.get(0);
		Assert.assertEquals("TCP/IP", protocol2.getName());
	}
}
