package com.oodd.spring.onetomanybidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanybidirectional.dao.ItemDao;
import com.oodd.spring.onetomanybidirectional.entity.Item;
@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Item item) {
		sessionFactory.getCurrentSession().save(item);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAll() {
		return (List<Item>)sessionFactory.getCurrentSession().
		createQuery("select item from Item item order by item.id desc ").list();
	}
	@Override
	public Item getById(Integer id) {
		return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
	}
	@Override
	public void delete(Item item) {
		sessionFactory.getCurrentSession().delete(item);
	}
	@Override
	public void update(Item item) {
		sessionFactory.getCurrentSession().merge(item);
	}
}
