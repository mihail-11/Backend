package com.example.reactlab.service;

import com.example.reactlab.dto.BookDto;
import com.example.reactlab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Page<Book> findAllWithPagination(Pageable pageable);



    Optional<Book> findBookById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id,BookDto bookDto);

    Optional<Book> deleteById(Long id);

    Optional<Book> markAsTaken(Long id);

    Optional<Book> unMarkAsTaken(Long id);
}
