package com.example.reactlab.service.impl;

import com.example.reactlab.dto.BookDto;
import com.example.reactlab.exceptions.AuthorNotFoundException;
import com.example.reactlab.exceptions.BookNotAvailableCopiesException;
import com.example.reactlab.exceptions.BookNotFoundException;
import com.example.reactlab.model.Author;
import com.example.reactlab.model.Book;
import com.example.reactlab.model.enumerations.Category;
import com.example.reactlab.repository.AuthorRepository;
import com.example.reactlab.repository.BookRepository;
import com.example.reactlab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.findAuthorById(bookDto.getAuthorId());
     return Optional.of( this.bookRepository.save(new Book(bookDto.getName(),author,bookDto.getAvailableCopies(),bookDto.getCategory())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.findBookById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.findAuthorById(bookDto.getAuthorId());

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Book book = this.findBookById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.deleteById(id);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findBookById(id).orElseThrow(() -> new BookNotFoundException(id));

        if (book.getAvailableCopies().equals(0)){
            throw new BookNotAvailableCopiesException(id);
        }
        else{
            book.setAvailableCopies(book.getAvailableCopies() - 1);
          return Optional.of(  this.bookRepository.save(book));
        }
    }

    @Override
    public Optional<Book> unMarkAsTaken(Long id) {
        Book book = this.findBookById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return Optional.of(this.bookRepository.save(book));
    }

    private Author findAuthorById(Long id){
      return   this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
