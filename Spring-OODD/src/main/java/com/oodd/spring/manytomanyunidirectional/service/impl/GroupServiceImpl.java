package com.oodd.spring.manytomanyunidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.GroupDao;
import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.entity.Group;
import com.oodd.spring.manytomanyunidirectional.mapper.GroupMapper;
import com.oodd.spring.manytomanyunidirectional.service.GroupService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public void create(GroupDto groupDto) {
		groupDao.insert(groupMapper.mapDtoToEntity(groupDto));
	}

	@Override
	public List<GroupDto> findAll() {
		List<Group> groups = groupDao.getAll();
		List<GroupDto> groupDtos = new ArrayList<GroupDto>();
		for(Group group : groups) {
			groupDtos.add(groupMapper.mapEntityToDto(group));
		}
		return groupDtos;
	}

	@Override
	public GroupDto findById(Integer id) {
		Group group = groupDao.getById(id);
		if(null != group) {
			return groupMapper.mapEntityToDto(group);
		}
		return null;
	}

	@Override
	public void remove(Integer groupId) {
		Group group = groupDao.getById(groupId);
		groupDao.delete(group);
	}

	@Override
	public void edit(GroupDto groupDto) {
		groupDao.update(groupMapper.mapDtoToEntity(groupDto));
	}

}
