package com.zenontechnology.libraryproject.dto;

import javax.persistence.Column;

import org.springframework.data.annotation.Transient;

public class BookAuthorDto {

	@Column(name = "BookName")
	private String BookName;

	@Column(name = "AuthorName")
	private String AuthorName;

	@Column(name = "BookId")
	private Long BookId;

	@Column(name = "AuthorId")
	private Long AuthorId;

	@Column(name = "BookImage")
	private String BookImage;

	public BookAuthorDto() {
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}

	public Long getBookId() {
		return BookId;
	}

	public void setBookId(Long bookId) {
		BookId = bookId;
	}

	public Long getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(Long authorId) {
		AuthorId = authorId;
	}

	public String getBookImage() {
		return BookImage;
	}

	public void setBookImage(String bookImage) {
		BookImage = bookImage;
	}

	@Transient
	public String getPhotosImagePath() {
		if (BookImage == null || BookId == null)
			return null;

		return "/user-photos/" + BookId + "/" + BookImage;
	}
}
