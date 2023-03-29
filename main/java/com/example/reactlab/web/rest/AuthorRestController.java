package com.example.reactlab.web.rest;

import com.example.reactlab.model.Author;
import com.example.reactlab.service.AuthorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> getAuthors(){
        return this.authorService.listAll();
    }
}
