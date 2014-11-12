package com.oodd.spring.manytomanyselfreference.dao;

import java.util.List;

import com.oodd.spring.manytomanyselfreference.entity.Member;

public interface MemberMemberDao {
	public List<Member> getAll();
	public List<Member> isPresent(Integer memberId1, Integer memberId2);
}
