package com.zenontechnology.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.UsersBook;

public interface UsersBookRepository extends JpaRepository<UsersBook, Long> {

	@Query("SELECT Count(b) FROM UsersBook b WHERE b.UserId = :UserId and b.UserBookId = :UserBookId")
	int CheckHasUserBook(@Param("UserId") Long UserId, @Param("UserBookId") Long UserBookId);

	@Query("SELECT b FROM UsersBook b WHERE b.UserId = :UserId")
	List<UsersBook> getUsersBookByUserId(@Param("UserId") Long UserId);

	@Query("SELECT Count(b) FROM UsersBook b WHERE b.UserId = :UserId")
	int UserBookNumberByUserId(@Param("UserId") Long UserId);

	@Query("SELECT b.UserBookName FROM UsersBook b WHERE b.UserBookId = :UserBookId")
	String getUsersBookNamebyBookId(@Param("UserBookId") Long UserBookId);

}
