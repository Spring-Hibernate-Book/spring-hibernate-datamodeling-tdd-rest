package com.oodd.spring.concretetableinheritance.dao;

import java.util.List;

import com.oodd.spring.concretetableinheritance.entity.Estate;

public interface EstateDao {
	public void insert(Estate estate) ;
	public List<Estate> getAll();
	public Estate getById(Integer id);
	public void delete(Estate estate) ;
	public void update(Estate estate);
}