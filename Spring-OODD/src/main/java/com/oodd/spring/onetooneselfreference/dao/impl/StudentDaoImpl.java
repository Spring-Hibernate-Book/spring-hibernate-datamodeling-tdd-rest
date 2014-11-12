package com.oodd.spring.onetooneselfreference.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetooneselfreference.dao.StudentDao;
import com.oodd.spring.onetooneselfreference.entity.Student;
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private SessionFactory sessionFactory ;

	@Override
	public void insert(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAll() {
		return (List<Student>)sessionFactory.getCurrentSession().
				createQuery("select student from Student student").list();
	}

	@Override
	public Student getById(Integer id) {
		return (Student)sessionFactory.getCurrentSession().get(Student.class, id);
	}

	@Override
	public void delete(Student student) {
		sessionFactory.getCurrentSession().delete(student);
	}

	@Override
	public void update(Student student) {
		sessionFactory.getCurrentSession().merge(student);
	}
}
