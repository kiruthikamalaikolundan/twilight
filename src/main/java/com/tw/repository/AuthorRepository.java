package com.tw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tw.model.Author;
//import com.tw.model.Book;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	
	
	@Query("select new com.tw.model.Author(au.id,au.name) from Author au")
	public List<Author> getingAllAuthors();

}
