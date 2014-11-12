package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao;

import java.util.List;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.ManuscriptAuthor;

public interface ManuscriptAuthorDao {
	public List<ManuscriptAuthor> getAll();
	public List<ManuscriptAuthor> isPresent(Integer manuscriptId, Integer authorId);
	public void delete(ManuscriptAuthor manuscriptAuthor);
}