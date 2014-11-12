package com.oodd.spring.manytomanyunidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.UserDao;
import com.oodd.spring.manytomanyunidirectional.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select user from User user order by user.id desc").list();
	}

	@Override
	public User getById(Integer id) {
		return (User) sessionFactory.getCurrentSession().get(User.class,id);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}
}
