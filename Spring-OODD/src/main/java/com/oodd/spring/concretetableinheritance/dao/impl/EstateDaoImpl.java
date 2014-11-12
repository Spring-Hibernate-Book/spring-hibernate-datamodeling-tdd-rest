package com.oodd.spring.concretetableinheritance.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.concretetableinheritance.dao.EstateDao;
import com.oodd.spring.concretetableinheritance.entity.Estate;

@Repository
@Transactional
public class EstateDaoImpl  implements EstateDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Estate estate) {
		sessionFactory.getCurrentSession().save(estate);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Estate> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select estate from Estate estate order by estate.id desc").list();
	}
	@Override
	public Estate getById(Integer id) {
		return (Estate) sessionFactory.getCurrentSession().get(Estate.class, id);
	}
	@Override
	public void delete(Estate estate) {
		sessionFactory.getCurrentSession().delete(estate);
	}
	@Override
	public void update(Estate estate) {
		sessionFactory.getCurrentSession().merge(estate);
	}
}
