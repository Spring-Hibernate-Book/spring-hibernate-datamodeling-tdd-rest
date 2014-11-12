package com.oodd.spring.onetomanybidirectional.service;

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

import com.oodd.spring.onetomanybidirectional.dto.ItemDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class ItemServiceImplTest {
	@Autowired
	private ItemService service;
	@Test
	public void testCreate(){
		ItemDto itemDto = new ItemDto();
		itemDto.setName("Book");
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("JEE");
		list.add("Spring");
		itemDto.setFeatureList(list);
		service.create(itemDto);
		Assert.assertEquals(1L, service.findAll().size());
	}
	@Test
	public void testEdit(){
		ItemDto itemDto = new ItemDto();
		itemDto.setName("Book");
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("JEE");
		list.add("Spring");
		itemDto.setFeatureList(list);
		service.create(itemDto);
		Assert.assertEquals(1L, service.findAll().size());
		
		List<ItemDto> itemDtos = service.findAll();
		ItemDto dto = itemDtos.get(0);
		list = new ArrayList<String>();
		list.add("EJB 3.0");
		dto.setFeatureList(list);
		service.edit(dto);
		Assert.assertEquals(1L, service.findAll().get(0).getFeatureList().size());
	}
	@Test
	public void testFindAll(){
		Assert.assertEquals(0L, service.findAll().size());
	}
	@Test
	public void testFindById() {
		ItemDto itemDto = new ItemDto();
		itemDto.setName("Book");
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("JEE");
		list.add("Spring");
		itemDto.setFeatureList(list);
		service.create(itemDto);
		
		List<ItemDto> itemDtos = service.findAll();
		ItemDto dto = itemDtos.get(0);
		ItemDto dto1 = service.findById(dto.getId());
		Assert.assertEquals("Book", dto1.getName());
		Assert.assertEquals(3L, dto1.getFeatureList().size());
	}
	@Test
	public void testRemove() {
		ItemDto itemDto1 = new ItemDto();
		itemDto1.setName("Book");
		List<String> list1 = new ArrayList<String>();
		list1.add("Java");
		list1.add("JEE");
		list1.add("Spring");
		itemDto1.setFeatureList(list1);
		service.create(itemDto1);
		
		ItemDto itemDto2 = new ItemDto();
		itemDto2.setName("Bags");
		List<String> list2 = new ArrayList<String>();
		list2.add("SkyBags");
		list2.add("WildCraft");
		list2.add("Puma");
		itemDto2.setFeatureList(list2);
		service.create(itemDto2);
		Assert.assertEquals(2L, service.findAll().size());
		
		List<ItemDto> itemDtos = service.findAll();
		
		ItemDto itemDto = itemDtos.get(1);
		service.remove(itemDto.getId());
		itemDtos = service.findAll();
		Assert.assertEquals(1L, service.findAll().size());
	}
}
