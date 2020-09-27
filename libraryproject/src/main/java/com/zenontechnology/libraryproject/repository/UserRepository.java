package com.zenontechnology.libraryproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.UserEmail = :UserEmail")
	public Users getByUserName(@Param("UserEmail") String UserEmail);
}
