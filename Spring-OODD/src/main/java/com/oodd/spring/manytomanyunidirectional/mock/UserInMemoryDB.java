package com.oodd.spring.manytomanyunidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;

public enum UserInMemoryDB {

	INSTANCE;
	
	private static List<UserDto> list = new ArrayList<UserDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(UserDto userDto) {
		userDto.setId(getId());
		list.add(userDto);
	}

	public void edit(UserDto userDto) {
		for (UserDto dto:list) {
			if (dto.getId()==userDto.getId()) {
				dto.setName(userDto.getName());
				
				List<UserGroupDto> userGroupList = UserGroupInMemoryDB.INSTANCE.findAll();
				List<UserGroupDto> modifiedUserGroupList = new ArrayList<UserGroupDto>();
				for(UserGroupDto userGroupDto : userGroupList) {
					if(dto.getId() == userGroupDto.getUserDto().getId()) {
						userGroupDto.getUserDto().setName(userDto.getName());						
					}
					modifiedUserGroupList.add(userGroupDto);
				}
				UserGroupInMemoryDB.INSTANCE.setList(modifiedUserGroupList);
			}
		}
	}
	
	public void remove(Integer id) {
		UserDto toRemove = null;
		for (UserDto dto : list) {
			if (dto.getId()==id) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) {
			list.remove(toRemove);
			List<UserGroupDto> userGroupList = UserGroupInMemoryDB.INSTANCE.findAll();
			List<UserGroupDto> modifiedUserGroupList = new ArrayList<UserGroupDto>();
			for(UserGroupDto userGroupDto : userGroupList) {
				if(toRemove.getId() != userGroupDto.getUserDto().getId()) {
					modifiedUserGroupList.add(userGroupDto);
				}
			}
			UserGroupInMemoryDB.INSTANCE.setList(modifiedUserGroupList);
		}
	}
	
	public List<UserDto> findAll() {
		return list;
	}
	
	public UserDto findById(Integer id) {
		for (UserDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
