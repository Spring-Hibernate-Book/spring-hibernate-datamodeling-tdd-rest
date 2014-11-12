package com.oodd.spring.manytomanybidirectionalwithjoinattribute.mapper;

import org.springframework.stereotype.Component;

import com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto.AuthorDto;
import com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity.Author;

@Component
public class AuthorMapper {
	public Author mapDtoToEntity(AuthorDto authorDto) {
		Author author = new Author();
		if(null != authorDto.getId()) author.setId(authorDto.getId());
		if(null != authorDto.getName()) author.setName(authorDto.getName());
		return author;
	}
	
	public AuthorDto mapEntityToDto(Author author) {
		AuthorDto authorDto = new AuthorDto();
		if(null != author.getId()) authorDto.setId(author.getId());
		if(null != author.getName()) authorDto.setName(author.getName());
		return authorDto;
	}
}