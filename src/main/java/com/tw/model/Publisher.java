package com.tw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String address;

	public Publisher() {
		
	}
	
	
	public Publisher(Integer id) {
		this.id = id;
	}


	public Publisher(Integer id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
}
