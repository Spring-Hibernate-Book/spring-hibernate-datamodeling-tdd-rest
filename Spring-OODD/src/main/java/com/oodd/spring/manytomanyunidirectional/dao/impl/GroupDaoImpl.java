package com.oodd.spring.manytomanyunidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.GroupDao;
import com.oodd.spring.manytomanyunidirectional.entity.Group;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {
	@Autowired
	private SessionFactory sessionFactory ;

	@Override
	public void insert(Group group) {
		sessionFactory.getCurrentSession().save(group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select group from Group group order by group.id desc").list();
	}

	@Override
	public Group getById(Integer id) {
		return (Group) sessionFactory.getCurrentSession().get(Group.class,id);
	}

	@Override
	public void delete(Group group) {
		sessionFactory.getCurrentSession().delete(group);
	}

	@Override
	public void update(Group group) {
		sessionFactory.getCurrentSession().merge(group);
	}
}
