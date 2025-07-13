package com.data.javarest04.service;

import com.data.javarest04.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookServiceInterface {
    Page<Book> findAll(Pageable pageable);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Long id);
    Book findBookById(Long id);
}
