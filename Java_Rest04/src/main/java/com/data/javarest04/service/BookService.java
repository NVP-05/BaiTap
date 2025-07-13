package com.data.javarest04.service;

import com.data.javarest04.entity.Book;
import com.data.javarest04.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BookServiceInterface {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
