package com.oodd.spring.manytomanyselfreference.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEMBER")
public class Member {

	private Integer id;
	private String name;
	private Set<Member> members1 = new HashSet<Member>();	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "MEMBER_MEMBER", 
				joinColumns = @JoinColumn(name = "MEMBER1_ID", referencedColumnName="ID"), 
				inverseJoinColumns = @JoinColumn(name = "MEMBER2_ID", referencedColumnName="ID"))
	public Set<Member> getMembers1() {
		return members1;
	}
	public void setMembers1(Set<Member> members1) {
		this.members1 = members1;
	}
}