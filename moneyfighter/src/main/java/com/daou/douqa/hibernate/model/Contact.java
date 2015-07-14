package com.daou.douqa.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer contactId;
	
	@Column(name="name")
	private String name;
	
	public Contact () {	
	}
	
	public Integer getContactId() {
		return contactId;
	}
	
	public void setContactId(Integer contactId) {
		this.contactId	= contactId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name	= name;
	}
}
