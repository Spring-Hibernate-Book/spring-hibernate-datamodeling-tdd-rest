package com.oodd.spring.manytomanyunidirectional.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyunidirectional.dao.UserGroupDao;
import com.oodd.spring.manytomanyunidirectional.entity.User;

@Repository
@Transactional
public class UserGroupDaoImpl implements UserGroupDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select distinct u from User u join u.groups g").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> isPresent(Integer userid, Integer groupid) {
		String hql = "select distinct u from User u join u.groups g where u.id=:userid and g.id=:groupid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userid", userid);
		query.setParameter("groupid", groupid);
		return query.list();
	}

}
