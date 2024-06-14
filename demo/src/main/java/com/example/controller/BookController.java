package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Book;
import com.example.model.User;
import com.example.service.userService;
import com.example.service.impl.BookServiceImpl;
import com.example.service.impl.UserServiceImpl;

import dto.BookDto;

@RestController
public class BookController {
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	com.example.service.bookService bookService;
	@Autowired
	UserServiceImpl userService;
	
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getBook(){
		return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> findBook(@PathVariable("id") int id){
		logger.info("Controller "+bookService.getBooks().toString());
		return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> saveBook(@RequestBody BookDto book) {
		return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
	}
	
	@PutMapping("/book/{id}")
	 public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
	    return new ResponseEntity<>(bookService.updateBook(id,book),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable int id) {
		return new ResponseEntity<>(bookService.deleteBookById(id), HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
		return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id) {
		return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}")
	 public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
	    return new ResponseEntity<>(userService.updateUser(id,user),HttpStatus.ACCEPTED);
	}
}
