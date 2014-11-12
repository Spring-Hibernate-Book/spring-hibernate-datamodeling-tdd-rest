package com.oodd.spring.manytomanybidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.entity.Client;

public interface ClientAccountDao {
	public List<Client> getAll();
	public List<Client> isPresent(Integer clientId, Integer accountId);
}
