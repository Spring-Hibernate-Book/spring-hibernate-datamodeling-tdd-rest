package com.oodd.spring.manytomanyunidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.GroupDao;
import com.oodd.spring.manytomanyunidirectional.dao.UserDao;
import com.oodd.spring.manytomanyunidirectional.dao.UserGroupDao;
import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;
import com.oodd.spring.manytomanyunidirectional.entity.Group;
import com.oodd.spring.manytomanyunidirectional.entity.User;
import com.oodd.spring.manytomanyunidirectional.mapper.GroupMapper;
import com.oodd.spring.manytomanyunidirectional.mapper.UserMapper;
import com.oodd.spring.manytomanyunidirectional.service.UserGroupService;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserGroupDao userGroupDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GroupMapper groupMapper;
		
	@Override
	public List<UserGroupDto> findAll() {
		List<UserGroupDto> userGroupDtos = new ArrayList<UserGroupDto>();
		List<User> userList = userGroupDao.getAll();
		for(User user : userList) {			
			UserDto userDto = userMapper.mapEntityToDto(user);			
			Set<Group> groups = user.getGroups();
			for(Group group : groups) {
				UserGroupDto userGroupDto = new UserGroupDto();
				userGroupDto.setUserDto(userDto);
				GroupDto groupDto = groupMapper.mapEntityToDto(group);
				userGroupDto.setGroupDto(groupDto);
				userGroupDtos.add(userGroupDto);
			}			
		}
		return userGroupDtos;
	}

	@Override
	public boolean isPresent(UserGroupDto userGroupDto) {
		boolean status = false;
		List<User> userList = userGroupDao.isPresent(userGroupDto.getUserDto().getId(), userGroupDto.getGroupDto().getId());
		if(null != userList) {
			if(userList.size() > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public void create(UserGroupDto userGroupDto) {
		Integer userid = userGroupDto.getUserDto().getId();
		Integer groupid = userGroupDto.getGroupDto().getId();
		
		User user = userDao.getById(userid);
		Group group = groupDao.getById(groupid);
		user.getGroups().add(group);
		
		userDao.insert(user);
	}

	@Override
	public void remove(UserGroupDto userGroupDto) {
		Integer userid = userGroupDto.getUserDto().getId();
		Integer groupid = userGroupDto.getGroupDto().getId();
		
		User user = userDao.getById(userid);
		Group group = groupDao.getById(groupid);
		
		user.getGroups().remove(group);
		userDao.update(user);
	}
}
