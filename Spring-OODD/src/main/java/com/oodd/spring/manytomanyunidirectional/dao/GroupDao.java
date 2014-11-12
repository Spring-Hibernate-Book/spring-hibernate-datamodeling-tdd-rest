package com.oodd.spring.manytomanyunidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanyunidirectional.entity.Group;

public interface GroupDao {
	public void insert(Group group);
	public List <Group>  getAll();
	public Group getById(Integer id);
	public void delete(Group group) ;
	public void update(Group group);
}
