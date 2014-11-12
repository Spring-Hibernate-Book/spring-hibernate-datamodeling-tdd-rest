/**
 * 
 */
package com.oodd.spring.singletableinheritance.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.singletableinheritance.dto.ProtocolDto;
import com.oodd.spring.singletableinheritance.dto.SNMPDto;
import com.oodd.spring.singletableinheritance.dto.TCPDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ProtocolServiceImplTest {
	@Autowired
	private ProtocolService service;
	
	@Test
	public void testCreate() {
		ProtocolDto sprotocol = new SNMPDto();
		sprotocol.setName("SNMP");
		service.create(sprotocol);
		
		ProtocolDto hProtocol = new TCPDto();
		hProtocol.setName("TCP/IP");
		service.create(hProtocol);
	
		Assert.assertEquals(2L, service.findAll().size());
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, service.findAll().size());
	}
	
	@Test
	public void testFindById() {
		ProtocolDto sprotocol = new SNMPDto();
		sprotocol.setName("SNMP");
		service.create(sprotocol);
	
		List<ProtocolDto> pList = service.findAll();
		ProtocolDto protocol = pList.get(0);
		
		ProtocolDto protocol2 = service.findById(protocol.getId());
		Assert.assertEquals("SNMP", protocol2.getName());
	}
	
	@Test
	public void testRemove() {
		ProtocolDto sprotocol = new SNMPDto();
		sprotocol.setName("SNMP");
		service.create(sprotocol);
		
		ProtocolDto hProtocol = new TCPDto();
		hProtocol.setName("TCP/IP");
		service.create(hProtocol);
	
		Assert.assertEquals(2L, service.findAll().size());
		
		List<ProtocolDto> pList = service.findAll();
		ProtocolDto protocol = pList.get(0);
		service.remove(protocol.getId());
		
		Assert.assertEquals(1L, service.findAll().size());
	}
	
	@Test
	public void testEdit() {
		ProtocolDto sprotocol = new SNMPDto();
		sprotocol.setName("SNMP");
		service.create(sprotocol);
		
		Assert.assertEquals(1L, service.findAll().size());

		List<ProtocolDto> pList = service.findAll();
		ProtocolDto protocol = pList.get(0);
		protocol.setName("SNMP1");
		service.edit(protocol);
		
		List<ProtocolDto> pList2 = service.findAll();
		ProtocolDto protocol2 = pList2.get(0);
		Assert.assertEquals("SNMP1", protocol2.getName());
	}
	
}
