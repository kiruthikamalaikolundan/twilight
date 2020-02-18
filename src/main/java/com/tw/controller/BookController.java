package com.tw.controller;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;
import com.tw.model.Author;
import com.tw.model.Book;
import com.tw.model.Publisher;
import com.tw.repository.BookRepository;
import com.tw.repository.BookRepositoryCustom;

@RestController
@RequestMapping("/api/bk")
public class BookController {
	
	
	@Autowired
	private BookRepository bookRepository;
	private BookRepositoryCustom bkc;
	
	@PersistenceContext
	EntityManager em;
	
	
	//fetch entire data
	@GetMapping("/booksss")
	public List<Book> gettingBooksCustom()
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Book> cq=cb.createQuery(Book.class);  
		Root<Book> book=cq.from(Book.class);  
		CriteriaQuery<Book> select = cq.select(book);  
		Query q = em.createQuery(select);
		List<Book> list = q.getResultList();		
		return list;
	}
	
	//fetch data in ascending order
	@GetMapping("/book-search")
	public List<Object[]> getColumn()
	{
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq=cb.createQuery(Object[].class);
		
		Root<Book> rootBook=cq.from(Book.class);
		Root<Author> rootAuthor=cq.from(Author.class);
		
		cq.multiselect(rootBook,rootAuthor);
		cq.where(cb.equal(rootBook.get("author"), rootAuthor.get("id")));
		
		Query q=em.createQuery(cq);
		@SuppressWarnings("unchecked")
		List<Object[]> list = q.getResultList();
		return list;
	}
	
	@GetMapping("/books")
	public List<Book> getAllBook()
	{
		return bookRepository.getAllBooks();
	}
	
	@GetMapping("/books/{id}")
	public Book getBooks(@PathVariable Integer id)
	{
		Optional<Book> op=bookRepository.findById(id);
		Book bk=null;
		if(op.isPresent())
		{
			bk=op.get();
		}
		else
		{
			System.out.println("Record not found");
		}
		
		return bk;
	}
	
	@GetMapping("/books2")
	public List<Book> getBookUser(@RequestParam String name)
	{
		return bookRepository.getBookName(name);
	}
	
	
	@GetMapping("/books1")
	public List<Book> getBookInbult(@RequestParam String name)
	{
		return bookRepository.findByNameContaining(name);
	}
	
	
	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book bk)
	{
		Integer authorId=bk.getAuthorId();
		Author author=new Author(authorId);
		bk.setAuthor(author);
		
		Integer publisherId=bk.getPublisherId();
		Publisher publisher=new Publisher(publisherId);
		bk.setPublisher(publisher);
		return bookRepository.save(bk);
	}
	
	@PutMapping("/books/{id}")
	public String updateBook(@PathVariable(value="id") int id,
			@Valid @RequestBody Book bookDetails)
	{
		Optional<Book> op=bookRepository.findById(id);
		if(op.isPresent())
		{
			Book bk=op.get();
			bk.setName(bookDetails.getName());
			bk.setPrice(bookDetails.getPrice());
			//bk.setAuthorId(bookDetails.getAuthorId());
			//bk.setPublisher(bookDetails.getPublisher());
			bookRepository.save(bk);
			return "updated successfully";
		}
		else
		{
			return "Record not found";
		}
		
	}
	
	@DeleteMapping("/books/{id}")
	public String deleteBook(@PathVariable(value="id") int id)
	{
		Optional<Book> op=bookRepository.findById(id);
		if(op.isPresent())
		{
			Book bk=op.get();
			bookRepository.delete(bk);
			return "Record deleted successfully";
		}
		else
		{
			return "Record not found";
		}
		
		
	}

}
