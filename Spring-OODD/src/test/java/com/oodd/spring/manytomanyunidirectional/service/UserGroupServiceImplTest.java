package com.oodd.spring.manytomanyunidirectional.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class UserGroupServiceImplTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	@Test
	public void testFindAll() {
		Assert.assertEquals(0L, userGroupService.findAll().size());
	}
	
	@Test
	public void testCreate() {
		UserGroupDto userGroupDto = new UserGroupDto();
		
		UserDto userDto = new UserDto();
		userDto.setName("Sara Tencradi");
		userService.create(userDto);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setName("Prison Break");
		groupService.create(groupDto);
		
		List<UserDto> userDtos = userService.findAll();
		UserDto userDto1 = userDtos.get(0);
		
		List<GroupDto> groupDtos = groupService.findAll();
		GroupDto groupDto1 = groupDtos.get(0);
		
		userGroupDto.setUserDto(userDto1);
		userGroupDto.setGroupDto(groupDto1);		
		
		userGroupService.create(userGroupDto);	
		Assert.assertEquals(1L, userGroupService.findAll().size());
	}
	
	@Test
	public void testIsPresent() {	
		UserGroupDto userGroupDto = new UserGroupDto();
		
		UserDto userDto = new UserDto();
		userDto.setName("Frodo Baggins");
		userService.create(userDto);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setName("The Lord of the Rings");
		groupService.create(groupDto);
		
		List<UserDto> userDtos = userService.findAll();
		UserDto userDto1 = userDtos.get(0);
		
		List<GroupDto> groupDtos = groupService.findAll();
		GroupDto groupDto1 = groupDtos.get(0);
		
		userGroupDto.setUserDto(userDto1);
		userGroupDto.setGroupDto(groupDto1);		
		
		userGroupService.create(userGroupDto);	
		Assert.assertEquals(1L, userGroupService.findAll().size());		
		
		boolean status = userGroupService.isPresent(userGroupDto);		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testRemove() {	
		UserGroupDto userGroupDto = new UserGroupDto();
		
		UserDto userDto = new UserDto();
		userDto.setName("Frodo Baggins");
		userService.create(userDto);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setName("The Lord of the Rings");
		groupService.create(groupDto);
		
		List<UserDto> userDtos = userService.findAll();
		UserDto userDto1 = userDtos.get(0);
		
		List<GroupDto> groupDtos = groupService.findAll();
		GroupDto groupDto1 = groupDtos.get(0);
		
		userGroupDto.setUserDto(userDto1);
		userGroupDto.setGroupDto(groupDto1);	
		
		userGroupService.create(userGroupDto);	
		Assert.assertEquals(1L, userGroupService.findAll().size());
		
		List<UserGroupDto> uList = userGroupService.findAll();
		UserGroupDto userGroupDto1 = uList.get(0);
		userGroupService.remove(userGroupDto1);
		
		Assert.assertEquals(0L, userGroupService.findAll().size());
	}
}