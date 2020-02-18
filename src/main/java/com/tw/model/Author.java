package com.tw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table
@Data
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String description;

	@Transient
	private String bookName;

	// @OneToMany(targetEntity=Book.class, cascade = CascadeType.ALL)
	// @JoinColumn(name="id",referencedColumnName="id")
	// @JsonIgnore

	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Book> books;

	public Author() {

	}

	public Author(int id) {
		this.id = id;
	}

	public Author(String name, String bookName) {
		this.name = name;
		this.bookName = bookName;
	}

	public Author(int id, String name) {

		this.id = id;
		this.name = name;

	}

	public Author(int id, String name, String address, String description) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}
}
