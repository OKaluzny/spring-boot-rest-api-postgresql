package com.kaluzny.web;

import com.kaluzny.domain.Book;
import com.kaluzny.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository repository;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookWithId(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @GetMapping(value = "/books", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Book> findBookWithName(@RequestParam(value = "name") String name) {
        return repository.findByName(name);
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBookFromDB(@PathVariable("id") long id, @RequestBody Book book) {
        return repository.save(putBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @DeleteMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBooks() {
        repository.deleteAll();
    }

    private Book putBook(long id, Book existingBook) {
        Book putBook = repository.findOne(id);
        putBook.setName(existingBook.getName());
        putBook.setDescription(existingBook.getDescription());
        putBook.setTags(existingBook.getTags());
        return putBook;
    }
}
