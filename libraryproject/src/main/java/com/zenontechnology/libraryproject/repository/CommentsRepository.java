package com.zenontechnology.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

	@Query("SELECT c FROM Comments c WHERE c.BookId = :BookId")
	public List<Comments> getBookCommentsByBookId(@Param("BookId") Long BookId);

	@Query("SELECT c FROM Comments c WHERE c.AuthorId = :AuthorId")
	public List<Comments> getAuthorCommentsByAuthorId(@Param("AuthorId") Long AuthorId);

	@Query("SELECT c FROM Comments c WHERE c.PublisherId = :PublisherId")
	public List<Comments> getPublisherCommentsByPublisherId(@Param("PublisherId") Long PublisherId);

	@Query("SELECT c FROM Comments c WHERE c.UserBookId = :UserBookId")
	public List<Comments> getUserBookCommentsByUserBookId(@Param("UserBookId") Long UserBookId);

	@Query(value = "SELECT * FROM comments c,users u WHERE c.UserId=u.UserId", nativeQuery = true)
	public List<Comments> listBooks();

}
