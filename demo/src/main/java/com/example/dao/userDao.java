package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface userDao extends CrudRepository<User, Integer>{
	public List<User> findByLastName(String lastName);
	public Optional<User> findById(long id);
	public List<User> findByFirstName(String firstName);
	public void deleteUserById(int id);
}
