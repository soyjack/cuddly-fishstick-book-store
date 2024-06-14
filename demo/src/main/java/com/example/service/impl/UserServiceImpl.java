package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.userDao;
import com.example.exceptions.BookNotFoundException;
import com.example.exceptions.UserNotFoundException;
import com.example.model.Book;
import com.example.model.User;
import com.example.service.userService;

@Service
public class UserServiceImpl implements userService {
	
	@Autowired
	userDao userD;
	

	@Override
	public User findUserById(int id) {
		Optional<User> usr = userD.findById(id);
		if(usr.isPresent()) {
			return usr.get();
		}else {
			throw new UserNotFoundException("User is not available with the given ID : "+id);
		}
	}

	@Override
	public List<User> findUserByLastName(String lastname) {
		List<User> list = userD.findByLastName(lastname);
		if(!list.isEmpty()) {
			return list;
		}else {
			throw new UserNotFoundException("User is not available with the given Last Name : "+lastname);
		}
	}

	@Override
	public List<User> findUserByFirstName(String firstname) {
		List<User> list = userD.findByFirstName(firstname);
		if(!list.isEmpty()) {
			return list;
		}else {
			throw new UserNotFoundException("User is not available with the given First Name : "+firstname);
		}
	}
	
	@Override
	public User saveUser(User user) {
		if(user.getFirstName().isBlank() || user.getFirstName().isEmpty() || user.getFirstName()==null) {
			throw new UserNotFoundException("User can not be saved in to the system");
		}else {
			return userD.save(user);
		}
	}
	
	@Override
	public String deleteUserById(int id) {
		User usr = findUserById(id);
        if(usr!=null){
		userD.deleteById(id);
		return "User is Deleted";
	} else {
		throw new UserNotFoundException("User Id : "+id+" does not exist");
		}
	}
	
	public List<User> findAllCustomer() {
		Iterable<User> customers =userD.findAll();
		List<User> list = new ArrayList<>();
		customers.forEach(list::add);
		return list;
		
	}
	
	@Override
	public User updateUser(int id, User user) {
		User usr = findUserById(id);
		if(usr!=null) {
			if(usr.getFirstName() != null)
				usr.setFirstName(usr.getFirstName());
			if(usr.getLastName() != null)
				usr.setLastName(usr.getLastName());
			
			userD.save(user);
			return usr;
		}
		else {
			throw new UserNotFoundException("User with given id: "+id+" is not available");
		}
		
	}	
}
