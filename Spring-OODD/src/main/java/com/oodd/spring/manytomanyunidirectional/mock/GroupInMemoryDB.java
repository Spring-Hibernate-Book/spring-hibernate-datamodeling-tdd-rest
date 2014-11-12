package com.oodd.spring.manytomanyunidirectional.mock;

import java.util.ArrayList;
import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.dto.UserGroupDto;



public enum GroupInMemoryDB {

	INSTANCE;
	
	private static List<GroupDto> list = new ArrayList<GroupDto>();
	private static Integer lastId = 0;
	
	public Integer getId() {
		return ++lastId;
	}
	
	public void add(GroupDto groupDto) {
		groupDto.setId(getId());
		list.add(groupDto);
	}

	public void edit(GroupDto groupDto) {
		for (GroupDto dto:list) {
			if (dto.getId()==groupDto.getId()) {
				dto.setName(groupDto.getName());
				
				List<UserGroupDto> userGroupList = UserGroupInMemoryDB.INSTANCE.findAll();
				List<UserGroupDto> modifiedUserGroupList = new ArrayList<UserGroupDto>();
				for(UserGroupDto userGroupDto : userGroupList) {
					if(dto.getId() == userGroupDto.getGroupDto().getId()) {
						userGroupDto.getGroupDto().setName(groupDto.getName());						
					}
					modifiedUserGroupList.add(userGroupDto);
				}
				UserGroupInMemoryDB.INSTANCE.setList(modifiedUserGroupList);
			}
		}
	}
	
	public void remove(Integer id) {
		GroupDto toRemove = null;
		for (GroupDto dto:list) 
			if (dto.getId()==id) toRemove = dto;
		if (toRemove!=null) {
			list.remove(toRemove);
			List<UserGroupDto> userGroupList = UserGroupInMemoryDB.INSTANCE.findAll();
			List<UserGroupDto> modifiedUserGroupList = new ArrayList<UserGroupDto>();
			for(UserGroupDto userGroupDto : userGroupList) {
				if(toRemove.getId() != userGroupDto.getGroupDto().getId()) {
					modifiedUserGroupList.add(userGroupDto);
				}
			}
			UserGroupInMemoryDB.INSTANCE.setList(modifiedUserGroupList);
		}
	}
	
	public List<GroupDto> findAll() {
		return list;
	}
	
	public GroupDto findById(Integer id) {
		for (GroupDto dto:list) {
			if (dto.getId()==id)
				return dto;
		}
		return null;
	}
}
