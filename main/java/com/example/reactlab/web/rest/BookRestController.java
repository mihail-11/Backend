package com.example.reactlab.web.rest;

import com.example.reactlab.dto.BookDto;
import com.example.reactlab.model.Book;
import com.example.reactlab.service.BookService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        return getResponseEntity( this.bookService.findBookById(id));
    }
    @GetMapping
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookService.findAllWithPagination(pageable);
    }


    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto){
        return getResponseEntity(this.bookService.save(bookDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Book> editBook(@PathVariable Long id,
                                          @RequestBody BookDto bookDto){
        return getResponseEntity(this.bookService.edit(id, bookDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        return getResponseEntity(this.bookService.deleteById(id));
    }

    @GetMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        return getResponseEntity(this.bookService.markAsTaken(id));
    }
//    @GetMapping("/unmark/{id}")
//    public ResponseEntity<Book> unmarkAsTaken(@PathVariable Long id){
//        return getResponseEntity(this.bookService.unMarkAsTaken(id));
//    }
    private ResponseEntity<Book> getResponseEntity(Optional<Book> bookOptional){
        return  bookOptional.map(el -> ResponseEntity.ok().body(el))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
