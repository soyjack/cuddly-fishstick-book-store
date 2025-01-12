package com.example.service;

import java.util.List;

import com.example.model.Book;
import com.example.model.User;

public interface userService {
	public User findUserById(int id);
	public List<User> findUserByLastName(String lastname);
	public List<User> findUserByFirstName(String firstname);
	public User saveUser(User user);
	public String deleteUserById(int id);
	public User updateUser(int id, User user);
}

