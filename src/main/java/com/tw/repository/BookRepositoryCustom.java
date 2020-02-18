package com.tw.repository;

import java.util.List;

import com.tw.model.Book;
//import com.tw.model.Author;

public interface BookRepositoryCustom {

	List<Book> findAll();
}
