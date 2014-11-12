package com.oodd.spring.manytomanyunidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.entity.User;

public interface UserDao {
	public void insert(User user);
	public List <User>  getAll();
	public User getById(Integer id);
	public void delete(User user) ;
	public void update(User user);
}
