package com.oodd.spring.manytomanybidirectionalwithjoinattribute.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AUTHOR")
public class Author {

	private Integer id;
	private String name;
	private Set<ManuscriptAuthor> manuscriptAuthors = new HashSet<ManuscriptAuthor>(0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="NAME", nullable=false, length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manuscriptAuthorId.author")
	public Set<ManuscriptAuthor> getManuscriptAuthors() {
		return manuscriptAuthors;
	}
	public void setManuscriptAuthors(Set<ManuscriptAuthor> manuscriptAuthors) {
		this.manuscriptAuthors = manuscriptAuthors;
	}
}