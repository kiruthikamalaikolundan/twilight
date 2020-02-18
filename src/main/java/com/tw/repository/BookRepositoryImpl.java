package com.tw.repository;
import java.util.ArrayList;

import java.util.List;

import com.tw.model.Author;
import com.tw.model.Book;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.tw.repository.BookRepositoryCustom;


public class BookRepositoryImpl implements BookRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Book> findAll() {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaQuery<Book> query = cb.createQuery(Book.class);
	Root<Book> bookRoot = query.from(Book.class);
	//Root authorRoot = query.from(Author.class);
	
    //cb.asc(bookRoot);
    //CriteriaQuery<Book> select=query.select(bookRoot);
    TypedQuery<Book> tq=entityManager.createQuery("select b from Book b",Book.class);
    List<Book> resultList=tq.getResultList();
	return resultList;
	
	
	}

}
