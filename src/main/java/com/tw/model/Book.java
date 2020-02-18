package com.tw.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.tw.model.Author;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private float price;
	
	@Transient
	private int authorId;
	@Transient
	private Integer publisherId;
	@Transient
	private String authorName;
	@Transient
	private String publisherName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	@JsonIgnore
	private Author author;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publisher_id")
	@JsonIgnore
	private Publisher publisher;
	

	public Book() {
		super();
	}
	
	public Book(int id, String name, float price, String authorName, String publisherName)
	{
		this.id=id;
		this.name=name;
		this.price=price;
		this.authorName=authorName;
		this.publisherName=publisherName;
		
	}
	
	public Book(int id,String name)
	{
		this.id=id;
		this.name=name;
	}

	public Book(int id) {
		super();
		this.id = id;
	}
	
	

	
	public Book(int id, String name, float price, int authorId, Integer publisherId, Author author,
			Publisher publisher) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.authorId = authorId;
		this.publisherId = publisherId;
		this.author = author;
		this.publisher = publisher;
	}
}
