package com.example.advices;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.exceptions.BookNotFoundException;

public class BookExceptionHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> handleProductNotFoundException(BookNotFoundException bookNotFoundException){

        Map<String, String> errorMap= new HashMap<>();

        errorMap.put("message", bookNotFoundException.getMessage());
        errorMap.put("timestamp", new Date().toString());
        errorMap.put("httpStatus", HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
 }
 
 @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException  methodArgumentNotValidException){
        Map<String, String> errorMap= new HashMap<>();

       methodArgumentNotValidException.getFieldErrors().forEach(error ->{
           errorMap.put(error.getField(), error.getDefaultMessage());
       });

        errorMap.put("timestamp", new Date().toString());
        errorMap.put("httpStatus", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
