package com.example.reactlab.model;

import com.example.reactlab.model.enumerations.Category;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Author author;

    private Integer availableCopies;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Book(String name, Author author, Integer availableCopies, Category category) {
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
        this.category = category;
    }

    public Book() {
    }
}
