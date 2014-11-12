package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.ManuscriptDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;

@Service
@Transactional
public class ManuscriptDaoImpl implements ManuscriptDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Manuscript manuscript) {
		sessionFactory.getCurrentSession().save(manuscript);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manuscript> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select manuscript from Manuscript manuscript order by manuscript.id desc").list();
	}

	@Override
	public Manuscript getById(Integer id) {
		return (Manuscript) sessionFactory.getCurrentSession().get(Manuscript.class,id);
	}

	@Override
	public void delete(Manuscript manuscript) {
		sessionFactory.getCurrentSession().delete(manuscript);
	}

	@Override
	public void update(Manuscript manuscript) {
		sessionFactory.getCurrentSession().merge(manuscript);
	}
}