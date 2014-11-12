package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.ManuscriptAuthorDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.ManuscriptAuthor;

@Repository
@Transactional
public class ManuscriptAuthorDaoImpl implements ManuscriptAuthorDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ManuscriptAuthor> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select distinct ma from ManuscriptAuthor ma").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ManuscriptAuthor> isPresent(Integer manuscriptId, Integer authorId) {		
		String hql = "select distinct ma from ManuscriptAuthor ma where ma.manuscriptAuthorId.manuscript.id=:manuscriptId and ma.manuscriptAuthorId.author.id=:authorId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("manuscriptId", manuscriptId);
		query.setParameter("authorId", authorId);
		return query.list();
	}
	
	@Override
	public void delete(ManuscriptAuthor manuscriptAuthor) {
		sessionFactory.getCurrentSession().delete(manuscriptAuthor);
	}
}