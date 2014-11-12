package com.oodd.spring.manytomanybidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.entity.Client;

public interface ClientDao {
	public void insert(Client client);
	public List <Client>  getAll();
	public Client getById(Integer id);
	public void delete(Client client) ;
	public void update(Client client);
}
