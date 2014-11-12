package com.oodd.spring.manytomanyunidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanyunidirectional.dto.GroupDto;
import com.oodd.spring.manytomanyunidirectional.entity.Group;

@Component
public class GroupMapper {

	public Group mapDtoToEntity(GroupDto groupDto) {
		Group group = new Group();
		if(null != groupDto.getId()) group.setId(groupDto.getId());
		if(null != groupDto.getName()) group.setName(groupDto.getName());
		return group;
	}
	
	public GroupDto mapEntityToDto(Group group) {
		GroupDto groupDto = new GroupDto();
		if(null != group.getId()) groupDto.setId(group.getId());
		if(null != group.getName()) groupDto.setName(group.getName());
		return groupDto;
	}
}
