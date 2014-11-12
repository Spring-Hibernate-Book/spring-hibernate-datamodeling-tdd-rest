package com.oodd.spring.manytomanyselfreference.dao;

import java.util.List;

import com.oodd.spring.manytomanyselfreference.entity.Member;

public interface MemberDao {
	public void insert(Member member);
	public List <Member>  getAll();
	public Member getById(Integer id);
	public void delete(Member member) ;
	public void update(Member member);
}