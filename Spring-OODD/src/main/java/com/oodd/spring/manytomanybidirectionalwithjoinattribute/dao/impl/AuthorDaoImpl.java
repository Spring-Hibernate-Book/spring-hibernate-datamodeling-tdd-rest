package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.AuthorDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.ManuscriptAuthor;

@Service
@Transactional
public class AuthorDaoImpl implements AuthorDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Author author) {
		sessionFactory.getCurrentSession().save(author);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select author from Author author order by author.id desc").list();
	}

	@Override
	public Author getById(Integer id) {
		return (Author) sessionFactory.getCurrentSession().get(Author.class,id);
	}

	@Override
	public void delete(Author author) {
		Set<ManuscriptAuthor> manuscriptAuthors = author.getManuscriptAuthors();
		for(ManuscriptAuthor manuscriptAuthor : manuscriptAuthors) {
			sessionFactory.getCurrentSession().delete(manuscriptAuthor);
		}
		sessionFactory.getCurrentSession().delete(author);
	}

	@Override
	public void update(Author author) {
		sessionFactory.getCurrentSession().merge(author);
	}
}