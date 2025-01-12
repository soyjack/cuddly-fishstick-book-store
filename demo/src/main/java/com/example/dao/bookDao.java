package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface bookDao extends CrudRepository<Book, Integer>{
}
