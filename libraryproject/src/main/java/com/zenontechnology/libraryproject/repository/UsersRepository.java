package com.zenontechnology.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenontechnology.libraryproject.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
