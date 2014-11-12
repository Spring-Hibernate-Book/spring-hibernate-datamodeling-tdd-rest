package com.oodd.spring.manytomanybidirectional.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectional.entity.Account;

public interface AccountDao {
	public void insert(Account account);
	public List <Account>  getAll();
	public Account getById(Integer id);
	public void delete(Account account) ;
	public void update(Account account);
}
