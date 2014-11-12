package com.oodd.spring.onetooneunidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneunidirectional.dao.BookDao;
import com.oodd.spring.onetooneunidirectional.entity.Book;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Book book) {
		sessionFactory.getCurrentSession().save(book);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAll() {
		return (List<Book>) sessionFactory.getCurrentSession()
		.createQuery("select book from Book book order by book.id desc").list();
	}
	@Override
	public Book getById(int id) {
		return (Book) sessionFactory.getCurrentSession().get(Book.class,id);
	}

	@Override
	public void delete(Book book) {
		sessionFactory.getCurrentSession().delete(book);
	}

	@Override
	public void update(Book book) {
		sessionFactory.getCurrentSession().merge(book);
	}

}
