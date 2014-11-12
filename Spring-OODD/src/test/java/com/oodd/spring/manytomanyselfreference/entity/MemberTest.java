package com.oodd.spring.manytomanyselfreference.entity;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreference.entity.Member;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class MemberTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCRUD() {
		Member member1 = new Member();
		member1.setName("Amritendu De");
		
		Member member2 = new Member();
		member2.setName("Lalit Narayan Mishra");
		
		Member member3 = new Member();
		member3.setName("Hazekul Alam");
		
		sessionFactory.getCurrentSession().save(member1);
		sessionFactory.getCurrentSession().save(member2);
		sessionFactory.getCurrentSession().save(member3);

		member1.setName("Amish Tripathi");
		sessionFactory.getCurrentSession().merge(member1);

		List<Member> list = sessionFactory.getCurrentSession().createQuery("from Member").list();
		Assert.assertEquals(3L, list.size());
		
		sessionFactory.getCurrentSession().delete(member1);
		
		List<Member> list2 = sessionFactory.getCurrentSession().createQuery("from Member").list();
		Assert.assertEquals(2L, list2.size());
		
		member2.getMembers1().add(member3);
		sessionFactory.getCurrentSession().merge(member2);

		List<Member> list3 = sessionFactory.getCurrentSession().createQuery("select distinct m from Member m join m.members1 m1").list();
		Assert.assertEquals(1L, list3.size());
		
		Member member4 = list3.get(0);
		Assert.assertEquals(1L, member4.getMembers1().size());
	}
}
