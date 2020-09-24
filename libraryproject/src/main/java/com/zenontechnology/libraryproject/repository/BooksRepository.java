package com.zenontechnology.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenontechnology.libraryproject.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

}
