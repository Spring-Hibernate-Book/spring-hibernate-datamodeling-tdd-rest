package com.oodd.spring.manytomanyselfreference.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.dao.MemberDao;
import com.oodd.spring.manytomanyselfreference.entity.Member;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SessionFactory sessionFactory ;

	@Override
	public void insert(Member member) {
		sessionFactory.getCurrentSession().save(member);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select m from Member m order by m.id desc").list();
	}

	@Override
	public Member getById(Integer id) {
		Member member = (Member)sessionFactory.getCurrentSession().get(Member.class,id);		
		return member;
	}
	
	@Override
	public void delete(Member member) {	
		sessionFactory.getCurrentSession().delete(member);
	}

	@Override
	public void update(Member member) {
		sessionFactory.getCurrentSession().merge(member);
	}
}