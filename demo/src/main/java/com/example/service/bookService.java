package com.example.service;

import java.util.List;

import com.example.model.Book;
import com.example.model.User;

import dto.BookDto;

public interface bookService {
	public Book saveBook(BookDto book);
	public List<Book> getBooks();
	public String deleteBookById(int id);
	public Book findBookById(int id);
	Book updateBook(int id, Book book);
	
}
