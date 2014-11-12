package com.oodd.spring.manytomanyunidirectional.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.entity.User;

@Component
public class UserMapper {

	public User mapDtoToEntity(UserDto userDto) {
		User user = new User();
		if(null != userDto.getId()) user.setId(userDto.getId());
		if(null != userDto.getName()) user.setName(userDto.getName());
		return user;
	}
	
	public UserDto mapEntityToDto(User user) {
		UserDto userDto = new UserDto();
		if(null != user.getId()) userDto.setId(user.getId());
		if(null != user.getName()) userDto.setName(user.getName());
		return userDto;
	}
}
