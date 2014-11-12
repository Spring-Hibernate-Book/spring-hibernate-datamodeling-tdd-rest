package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;

public interface ManuscriptDao {
	public void insert(Manuscript manuscript);
	public List <Manuscript>  getAll();
	public Manuscript getById(Integer id);
	public void delete(Manuscript manuscript) ;
	public void update(Manuscript manuscript);
}
