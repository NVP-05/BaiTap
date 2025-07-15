package com.data.javarest06.service;

import com.data.javarest06.model.entity.Book;
import com.data.javarest06.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IService<Book,Long> {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
