package com.example.reactlab.exceptions;

public class BookNotAvailableCopiesException extends RuntimeException{
    public BookNotAvailableCopiesException(Long id){
        super("Book with id = " + id + " does not have available copy!");
    }
}
