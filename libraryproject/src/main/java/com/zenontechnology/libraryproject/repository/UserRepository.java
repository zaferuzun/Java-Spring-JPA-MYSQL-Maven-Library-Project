package com.zenontechnology.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.UserEmail = :UserEmail")
	public Users getByUserName(@Param("UserEmail") String UserEmail);

	@Query("SELECT u FROM Users u")
	public List<Users> listAll();

	@Query("SELECT u FROM Users u WHERE u.UserId = :UserId")
	public Users getByUserId(@Param("UserId") Long UserId);

	@Query("SELECT u.UserEmail FROM Users u WHERE u.UserId = :UserId")
	String getUserNamebyUserId(@Param("UserId") Long UserId);

	@Query("SELECT u.UserId FROM Users u WHERE u.UserEmail = :UserEmail")
	Long getUserIdbyUserEmail(@Param("UserEmail") String UserEmail);

	@Query("SELECT Count(UserId) FROM Users u WHERE u.UserEmail = :UserEmail")
	int userCheck(@Param("UserEmail") String UserEmail);

}
