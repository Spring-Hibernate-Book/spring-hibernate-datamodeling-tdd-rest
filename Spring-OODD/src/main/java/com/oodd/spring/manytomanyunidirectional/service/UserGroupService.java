package com.oodd.spring.manytomanyunidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;

public interface UserGroupService {

	public boolean isPresent(UserGroupDto userGroupDto);
	public List<UserGroupDto> findAll();
	public void create(UserGroupDto userGroupDto);
	public void remove(UserGroupDto userGroupDto);
}
