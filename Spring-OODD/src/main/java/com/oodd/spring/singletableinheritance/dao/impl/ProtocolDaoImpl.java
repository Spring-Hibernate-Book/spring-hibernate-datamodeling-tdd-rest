package com.oodd.spring.singletableinheritance.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.singletableinheritance.dao.ProtocolDao;
import com.oodd.spring.singletableinheritance.entity.Protocol;

@Repository
@Transactional
public class ProtocolDaoImpl  implements ProtocolDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Protocol protocol) {
		sessionFactory.getCurrentSession().save(protocol);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Protocol> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select protocol from Protocol protocol order by protocol.id desc").list();
	}
	@Override
	public Protocol getById(Integer id) {
		return (Protocol) sessionFactory.getCurrentSession().get(Protocol.class, id);
	}
	@Override
	public void delete(Protocol protocol) {
		sessionFactory.getCurrentSession().delete(protocol);
	}
	@Override
	public void update(Protocol protocol) {
		sessionFactory.getCurrentSession().merge(protocol);
	}
}
