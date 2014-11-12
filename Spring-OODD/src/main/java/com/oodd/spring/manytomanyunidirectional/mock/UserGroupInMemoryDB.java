package com.oodd.spring.manytomanyunidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;

public enum UserGroupInMemoryDB {

	INSTANCE;
	
	private static List<UserGroupDto> list = new ArrayList<UserGroupDto>();
	
	public void add(UserGroupDto userGroupDto) {
		list.add(userGroupDto);
	}

	public boolean isPresent(UserGroupDto userGroupDto) {
		for (UserGroupDto dto:list) {
			if (dto.getUserDto().getId() == userGroupDto.getUserDto().getId() 
					&& dto.getGroupDto().getId() == userGroupDto.getGroupDto().getId()) {
				return true;
			}				
		}
		return false;
	}
	
	public void remove(UserGroupDto userGroupDto) {
		UserGroupDto toRemove = null;
		for (UserGroupDto dto:list) {
			if (dto.getUserDto().getId()==userGroupDto.getUserDto().getId()
					&& dto.getGroupDto().getId()==userGroupDto.getGroupDto().getId()) {
				toRemove = dto;
			}
		}			
		if (toRemove!=null) list.remove(toRemove);
	}
	
	public List<UserGroupDto> findAll() {
		return list;
	}
	
	public void setList(List<UserGroupDto> list) {
		UserGroupInMemoryDB.list = list;
	}
}