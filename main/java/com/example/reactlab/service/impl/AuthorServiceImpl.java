package com.example.reactlab.service.impl;

import com.example.reactlab.model.Author;
import com.example.reactlab.repository.AuthorRepository;
import com.example.reactlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository
                .findAll();
    }
}
