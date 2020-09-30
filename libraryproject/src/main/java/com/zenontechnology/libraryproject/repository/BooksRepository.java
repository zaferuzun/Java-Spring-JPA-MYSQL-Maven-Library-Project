package com.zenontechnology.libraryproject.repository;

import java.util.List;

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

	@Query("SELECT Count(BookId) FROM Books")
	int AllBookNumbers();

	@Query(value = "SELECT * FROM Books  ORDER BY RAND() LIMIT :limitNumber", nativeQuery = true)
	public List<Books> listRandomBooks(@Param("limitNumber") int limitNumber);

	@Query("SELECT b FROM Books b WHERE b.AuthorId = :AuthorId")
	public List<Books> listBooksByAuthorId(@Param("AuthorId") Long AuthorId);

}
