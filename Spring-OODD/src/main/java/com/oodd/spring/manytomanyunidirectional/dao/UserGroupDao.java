package com.oodd.spring.manytomanyunidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.entity.User;

public interface UserGroupDao {
	public List<User> getAll();
	public List<User> isPresent(Integer userid, Integer groupid);
}
