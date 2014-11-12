package com.oodd.spring.onetooneselfreference.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="STUDENT")
public class Student {
	private Integer id;
	private String name;
	private Student mentor;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID",length=20)
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="mentor_id",nullable=true)
	public Student getMentor() {
		return mentor;
	}
	public void setMentor(Student mentor) {
		this.mentor = mentor;
	}
}
