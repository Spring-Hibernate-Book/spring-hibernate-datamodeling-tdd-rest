package com.oodd.spring.manytomanyunidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;

public interface GroupService {

	public void create(GroupDto groupDto ) ;
	public List<GroupDto> findAll();
	public GroupDto findById(Integer id);
	public void remove(Integer groupId);
	public void edit(GroupDto groupDto);
}
