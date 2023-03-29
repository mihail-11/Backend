package com.example.reactlab.configuration;

import com.example.reactlab.model.Author;
import com.example.reactlab.model.Book;
import com.example.reactlab.model.Country;
import com.example.reactlab.model.enumerations.Category;
import com.example.reactlab.repository.AuthorRepository;
import com.example.reactlab.repository.BookRepository;
import com.example.reactlab.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeclareData {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private  final CountryRepository countryRepository;

    public DeclareData(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        Country Macedonia =  this.countryRepository.save(new Country("Macedonia","Europe"));
        Country Canada =  this.countryRepository.save(new Country("Canada","North America"));
        Country Russia =   this.countryRepository.save(new Country("Russia","Europe"));
        Country Norway =  this.countryRepository.save(new Country("Norway","Europe"));
        Country Iceland =  this.countryRepository.save(new Country("Iceland","Europe"));
        Country France =  this.countryRepository.save(new Country("France","Europe"));


        Author Fyodor = this.authorRepository.save(new Author("Fyodor", "Dostoevsky",Russia));
        Author Petre = this.authorRepository.save(new Author("Petre", "Prlickov",Macedonia));
        Author Stojan = this.authorRepository.save(new Author("Stojan", "Tarapuza",Macedonia));
        Author Andreevski = this.authorRepository.save(new Author("Petre", "Andreevski",Macedonia));
        Author Balzak = this.authorRepository.save(new Author("Onore", "Balzak",France));


        this.bookRepository.save(new Book("Pirej",Andreevski,5, Category.THRILLER));
        this.bookRepository.save(new Book("Teskoto",Petre,2, Category.DRAMA));
        this.bookRepository.save(new Book("Zlostorstvo i kazna",Fyodor,1, Category.DRAMA));
        this.bookRepository.save(new Book("Sangrinska koza",Balzak,8, Category.DRAMA));
        this.bookRepository.save(new Book("Crnila",Andreevski,10, Category.DRAMA));



    }
}
