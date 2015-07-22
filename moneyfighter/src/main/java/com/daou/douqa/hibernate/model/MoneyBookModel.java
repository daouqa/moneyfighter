package com.daou.douqa.hibernate.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
@Table(name="MONEYBOOK")
public class MoneyBookModel {

	@Id
	@Column(name="id")
	@GeneratedValue
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recordId;
	
	@Column(name="date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	
	@Column(name="type", length=256)
	private String type;
	
	@Column(name="category", length=256)
	private String category;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="note")
	@Type(type="text")
	private String note;
	
	public MoneyBookModel () {	
	}
	
	public void setRecordId(Integer recordId) {
		this.recordId	= recordId;
	}
	
	public void setDate(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt = formatter.parseDateTime(date);
		this.date	= dt;
	}

	public void setType(String type) {
		this.type	= type;
	}

	public void setCategory(String category) {
		this.category	= category;	
	}

	public void setPrice(int price) {
		this.price	= price;
	}

	public void setNote(String note) {
		this.note	= note;
	}

	public Integer getRecordId() {
		return this.recordId;
	}

	public String getDate() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dt					= this.date;
		String dateString			= dt.toString(formatter);
		
		return dateString;
	}

	public String getType() {
		return this.type;
	}

	public String getCategory() {
		return this.category;
	}

	public Integer getPrice() {
		return this.price;
	}

	public String getNote() {
		return this.note;
	}

}
