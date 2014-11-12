package com.oodd.spring.manytomanyunidirectional.dto;


public class UserGroupDto {
	
	private UserDto userDto;
	private GroupDto groupDto;
	
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public GroupDto getGroupDto() {
		return groupDto;
	}
	public void setGroupDto(GroupDto groupDto) {
		this.groupDto = groupDto;
	}
}
