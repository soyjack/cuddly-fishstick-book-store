package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.bookDao;
import com.example.dao.Impl.BookDaoImpl;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.UserNotFoundException;
import com.example.model.Book;
import com.example.model.User;
import com.example.service.bookService;
import com.example.service.userService;

import dto.BookDto;
import lombok.extern.slf4j.Slf4j;

@Service
public class BookServiceImpl implements bookService{
	private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	bookDao bookD;
	
	@Autowired
	userService usrService;
	
	@Override
	public List<Book> getBooks(){
		Iterable<Book> books = bookD.findAll();
		logger.info(books.toString());
		List<Book> list = new ArrayList<>();
		books.forEach(list::add);
		logger.info(list.toString());
		if(list.isEmpty()) {
			throw new BookNotFoundException("No Books Available!");
		}
		return list;
	}

	@Override
	public Book saveBook(BookDto book) {
		if (book.userId()<0) {
			throw new UserNotFoundException("Please provide valid input ");
		}
		Book newBook = new Book();
		newBook.setBookName(book.bookName());
		newBook.setBookDescription(book.bookDescription());
		User u= usrService.findUserById(book.userId());
		if(u!=null) {
			newBook.setUser(u);
			return bookD.save(newBook);
		}else
			throw new UserNotFoundException("User Id : " + u.getId() +  " does not exit ");
		
	}

	@Override
	public String deleteBookById(int id) {
		Book bok = findBookById(id);
        if(bok!=null){
        	bookD.deleteById(id);
		return "User is Deleted";
	} else {
		throw new BookNotFoundException("Book Id : "+id+" does not exist");
		}
	}

	@Override
	public Book findBookById(int id) {
		Optional<Book> book = bookD.findById(id);
		if(book.isPresent()) {
			return book.get();
		}else {
			throw new BookNotFoundException("No Books Available!");
		}
	}
	
	@Override
	public Book updateBook(int id, Book book) {
		Book bok = findBookById(id);
		if(bok!=null) {
			if(bok.getBookName() != null)
				bok.setBookName(bok.getBookName());
			if(bok.getBookDescription() != null)
				bok.setBookDescription(bok.getBookDescription());
			bookD.save(book);
			return bok;
		}
		else {
			throw new BookNotFoundException("Book with given id: "+id+" is not available");
		}
		
	}
}
