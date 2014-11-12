package com.oodd.spring.singletableinheritance.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PROTOCOL")
@DiscriminatorValue("SNMP")
public class SNMP extends Protocol {

}
