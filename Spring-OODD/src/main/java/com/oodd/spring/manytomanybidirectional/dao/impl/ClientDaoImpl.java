package com.oodd.spring.manytomanybidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.ClientDao;
import com.oodd.spring.manytomanybidirectional.entity.Client;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Client client) {
		sessionFactory.getCurrentSession().save(client);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select client from Client client order by client.id desc").list();
	}

	@Override
	public Client getById(Integer id) {
		return (Client) sessionFactory.getCurrentSession().get(Client.class,id);
	}

	@Override
	public void delete(Client client) {
		sessionFactory.getCurrentSession().delete(client);
	}

	@Override
	public void update(Client client) {
		sessionFactory.getCurrentSession().merge(client);
	}

}
