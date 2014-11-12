package com.oodd.spring.singletableinheritance.dao;

import java.util.List;

import com.oodd.spring.singletableinheritance.entity.Protocol;

public interface ProtocolDao {
	public void insert(Protocol protocol) ;
	public List<Protocol> getAll();
	public Protocol getById(Integer id);
	public void delete(Protocol protocol) ;
	public void update(Protocol protocol);
}