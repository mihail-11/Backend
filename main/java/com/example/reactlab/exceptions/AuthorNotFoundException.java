package com.example.reactlab.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id){
        super("Author with id = " + id + " was not found!");
    }
}
