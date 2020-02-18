package com.tw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tw.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select new com.tw.model.Book(b.id as bookId,b.name,b.price,au.name as authorName,pu.name) from Book b join b.author au join b.publisher pu")
	public List<Book> getAllBooks();
	
	@Query("select new com.tw.model.Book(b.id as bookId,b.name as bookName) from Book b where b.name like %:name%")
	public List<Book> getBookName(@Param("name") String name);
	
	public List<Book> findByNameContaining(String name);	
	
}
