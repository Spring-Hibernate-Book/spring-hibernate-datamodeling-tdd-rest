package com.oodd.spring.manytomanybidirectionalwithjoinattribute.dto;


public class ManuscriptAuthorDto {

	private ManuscriptDto manuscriptDto;
	private AuthorDto authorDto;
	private String publisher;
	
	
	public ManuscriptDto getManuscriptDto() {
		return manuscriptDto;
	}
	public void setManuscriptDto(ManuscriptDto manuscriptDto) {
		this.manuscriptDto = manuscriptDto;
	}
	public AuthorDto getAuthorDto() {
		return authorDto;
	}
	public void setAuthorDto(AuthorDto authorDto) {
		this.authorDto = authorDto;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}