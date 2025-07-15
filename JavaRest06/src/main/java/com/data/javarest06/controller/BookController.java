package com.data.javarest06.controller;

import com.data.javarest06.model.entity.Book;
import com.data.javarest06.model.response.DataResponse;
import com.data.javarest06.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Book>>> findAll() {
        return new ResponseEntity<>(
                new DataResponse<>(bookService.findAll(), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> save(@RequestBody Book book) {
        return new ResponseEntity<>(
                new DataResponse<>(bookService.save(book), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> update(@RequestBody Book book) {
        return new ResponseEntity<>(
                new DataResponse<>(bookService.update(book), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(
                new DataResponse<>(null, HttpStatus.OK),
                HttpStatus.OK
        );
    }
}
