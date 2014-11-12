package com.oodd.spring.manytomanybidirectional.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectional.dao.ClientAccountDao;
import com.oodd.spring.manytomanybidirectional.entity.Client;

@Repository
@Transactional
public class ClientAccountDaoImpl implements ClientAccountDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select distinct c from Client c join c.accounts a").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> isPresent(Integer clientid, Integer accountid) {
		String hql = "select distinct c from Client c join c.accounts a where c.id=:clientid and a.id=:accountid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("clientid", clientid);
		query.setParameter("accountid", accountid);
		return query.list();
	}

}
