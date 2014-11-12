package com.oodd.spring.manytomanyunidirectional.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.UserDao;
import com.oodd.spring.manytomanyunidirectional.dto.UserDto;
import com.oodd.spring.manytomanyunidirectional.entity.User;
import com.oodd.spring.manytomanyunidirectional.mapper.UserMapper;
import com.oodd.spring.manytomanyunidirectional.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void create(UserDto userDto) {
		userDao.insert(userMapper.mapDtoToEntity(userDto));
	}

	@Override
	public List<UserDto> findAll() {
		List<User> users = userDao.getAll();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for(User user : users) {
			userDtos.add(userMapper.mapEntityToDto(user));
		}
		return userDtos;
	}

	@Override
	public UserDto findById(Integer id) {
		User user = userDao.getById(id);
		if(null != user) {
			return userMapper.mapEntityToDto(user);
		}
		return null;
	}

	@Override
	public void remove(Integer userId) {
		User user = userDao.getById(userId);
		userDao.delete(user);
	}

	@Override
	public void edit(UserDto userDto) {
		User userCurrent = userDao.getById(userDto.getId());
		User userToBeEdited = userMapper.mapDtoToEntity(userDto);
		userToBeEdited.setGroups(userCurrent.getGroups());
		userDao.update(userToBeEdited);
	}
}
