package com.oodd.spring.manytomanyselfreference.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.dao.MemberMemberDao;
import com.oodd.spring.manytomanyselfreference.entity.Member;

@Repository
@Transactional
public class MemberMemberDaoImpl implements MemberMemberDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select distinct m from Member m join m.members1 m1").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> isPresent(Integer memberId1, Integer memberId2) {
		String hql = "select distinct m from Member m join m.members1 m1 where m.id=:memberId1 and m1.id=:memberId2";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("memberId1", memberId1);
		query.setParameter("memberId2", memberId2);
		return query.list();
	}
}