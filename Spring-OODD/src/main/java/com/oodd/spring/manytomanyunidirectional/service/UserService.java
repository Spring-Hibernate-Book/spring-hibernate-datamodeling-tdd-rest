package com.oodd.spring.manytomanyunidirectional.service;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.dto.UserDto;

public interface UserService {

	public void create(UserDto userDto ) ;
	public List<UserDto> findAll();
	public UserDto findById(Integer id);
	public void remove(Integer userId);
	public void edit(UserDto userDto);
}
