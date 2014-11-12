package com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.AuthorDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.ManuscriptAuthorDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dao.ManuscriptDao;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.ManuscriptAuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Manuscript;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.ManuscriptAuthor;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper.AuthorMapper;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper.ManuscriptMapper;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.service.ManuscriptAuthorService;

@Service
@Transactional
public class ManuscriptAuthorServiceImpl implements ManuscriptAuthorService {

	@Autowired
	private ManuscriptDao manuscriptDao;
	
	@Autowired
	private ManuscriptMapper manuscriptMapper;
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private ManuscriptAuthorDao manuscriptAuthorDao;
	
	
	@Override
	public boolean isPresent(ManuscriptAuthorDto manuscriptAuthorDto) {
		boolean status = false;
		List<ManuscriptAuthor> manuscriptAuthorList = manuscriptAuthorDao.isPresent(manuscriptAuthorDto.getManuscriptDto().getId(), manuscriptAuthorDto.getAuthorDto().getId());
		if(null != manuscriptAuthorList) {
			if(manuscriptAuthorList.size() > 0) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public List<ManuscriptAuthorDto> findAll() {
		List<ManuscriptAuthorDto> manuscriptAuthorDtos = new ArrayList<ManuscriptAuthorDto>();
		List<ManuscriptAuthor> manuscriptList = manuscriptAuthorDao.getAll();
		for(ManuscriptAuthor manuscriptAuthor : manuscriptList) {	
			ManuscriptAuthorDto manuscriptAuthorDto = new ManuscriptAuthorDto();	
			manuscriptAuthorDto.setManuscriptDto(manuscriptMapper.mapEntityToDto(manuscriptAuthor.getManuscript()));
			manuscriptAuthorDto.setAuthorDto(authorMapper.mapEntityToDto(manuscriptAuthor.getAuthor()));
			manuscriptAuthorDto.setPublisher(manuscriptAuthor.getPublisher());
			manuscriptAuthorDtos.add(manuscriptAuthorDto);
		}
		return manuscriptAuthorDtos;
	}

	@Override
	public void create(ManuscriptAuthorDto manuscriptAuthorDto) {
		Integer manuscriptId = manuscriptAuthorDto.getManuscriptDto().getId();
		Integer authorId = manuscriptAuthorDto.getAuthorDto().getId();
		
		Manuscript manuscript = manuscriptDao.getById(manuscriptId);
		Author author = authorDao.getById(authorId);
		
		ManuscriptAuthor manuscriptAuthor = new ManuscriptAuthor();
		manuscriptAuthor.setManuscript(manuscript);
		manuscriptAuthor.setAuthor(author);
		manuscriptAuthor.setPublisher(manuscriptAuthorDto.getPublisher());
		
		manuscript.getManuscriptAuthors().add(manuscriptAuthor);
		manuscriptDao.insert(manuscript);
	}

	@Override
	public void remove(ManuscriptAuthorDto manuscriptAuthorDto) {
		Integer manuscriptId = manuscriptAuthorDto.getManuscriptDto().getId();
		Integer authorId = manuscriptAuthorDto.getAuthorDto().getId();
		
		Manuscript manuscript = manuscriptDao.getById(manuscriptId);		
		Author author = authorDao.getById(authorId);
		
		List<ManuscriptAuthor> manuscriptAuthorList = manuscriptAuthorDao.isPresent(manuscriptId, authorId);
		for(ManuscriptAuthor manuscriptAuthor : manuscriptAuthorList) {
			manuscript.getManuscriptAuthors().remove(manuscriptAuthor);
			author.getManuscriptAuthors().remove(manuscriptAuthor);
			manuscriptAuthorDao.delete(manuscriptAuthor);
		}	
		authorDao.update(author);
		manuscriptDao.update(manuscript);
	}
}