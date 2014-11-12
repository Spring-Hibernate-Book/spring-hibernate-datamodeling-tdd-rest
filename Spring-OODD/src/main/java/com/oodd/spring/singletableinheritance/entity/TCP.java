package com.oodd.spring.singletableinheritance.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PROTOCOL")
@DiscriminatorValue("TCP")
public class TCP extends Protocol {
	
}