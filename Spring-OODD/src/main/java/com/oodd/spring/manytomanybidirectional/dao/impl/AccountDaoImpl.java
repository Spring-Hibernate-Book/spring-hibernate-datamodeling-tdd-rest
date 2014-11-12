package com.oodd.spring.manytomanybidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.AccountDao;
import com.oodd.spring.manytomanybidirectional.entity.Account;
import com.oodd.spring.manytomanybidirectional.entity.Client;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select account from Account account order by account.id desc").list();
	}

	@Override
	public Account getById(Integer id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class,id);
	}

	@Override
	public void delete(Account account) {		
		for(Client client : account.getClients()) {
			client.getAccounts().remove(account);			
		}
		sessionFactory.getCurrentSession().delete(account);
	}

	@Override
	public void update(Account account) {
		sessionFactory.getCurrentSession().merge(account);
	}

}
