package com.zenontechnology.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenontechnology.libraryproject.entity.Authors;

public interface AuthorsRepository extends JpaRepository<Authors, Long> {

}
