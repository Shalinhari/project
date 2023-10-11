package com.library.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
