package com.library.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.entity.Book;
import com.library.project.repository.BookRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book updateDate(Book existingBook) {
        LocalDate currentDate = LocalDate.now();
        existingBook.setEntryDate(currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        existingBook.setDueDate(currentDate.plusDays(10).format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        return bookRepository.save(existingBook);
    }
}

