package com.zenontechnology.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

	@Query("SELECT BookId FROM Books b WHERE b.BookId = 2")
	int findAllActiveUsers();

	@Query("SELECT Count(AuthorId) FROM Books b WHERE b.AuthorId = :AuthorId")
	int AuthorBookNumber(@Param("AuthorId") Long AuthorId);

	@Query("SELECT Count(PublisherId) FROM Books b WHERE b.PublisherId = :PublisherId")
	int PublisherBookNumber(@Param("PublisherId") Long PublisherId);
}
