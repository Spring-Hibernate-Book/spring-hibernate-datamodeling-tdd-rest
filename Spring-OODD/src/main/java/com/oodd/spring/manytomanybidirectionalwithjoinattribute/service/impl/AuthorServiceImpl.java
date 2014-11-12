package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.AuthorDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper.AuthorMapper;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Override
	public void create(AuthorDto authorDto) {
		authorDao.insert(authorMapper.mapDtoToEntity(authorDto));
	}

	@Override
	public List<AuthorDto> findAll() {
		List<Author> authors = authorDao.getAll();
		List<AuthorDto> authorDtos = new ArrayList<AuthorDto>();
		for(Author author : authors) {
			authorDtos.add(authorMapper.mapEntityToDto(author));
		}
		return authorDtos;
	}

	@Override
	public AuthorDto findById(Integer id) {
		Author author = authorDao.getById(id);
		if(null != author) {
			return authorMapper.mapEntityToDto(author);
		}
		return null;
	}

	@Override
	public void remove(Integer id) {
		Author author = authorDao.getById(id);
		authorDao.delete(author);
	}

	@Override
	public void edit(AuthorDto authorDto) {
		Author authorCurrent = authorDao.getById(authorDto.getId());
		Author authorToBeEdited = authorMapper.mapDtoToEntity(authorDto);
		authorToBeEdited.setManuscriptAuthors(authorCurrent.getManuscriptAuthors());
		authorDao.update(authorToBeEdited);
	}
}