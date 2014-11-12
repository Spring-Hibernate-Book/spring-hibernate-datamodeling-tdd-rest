package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao;

import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;

public interface WorkerDao {
	public void insert(Worker worker);
	public List <Worker>  getAll();
	public Worker getById(Integer id);
	public void delete(Worker worker) ;
	public void update(Worker worker);
}
