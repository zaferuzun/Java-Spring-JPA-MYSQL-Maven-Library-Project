package com.zenontechnology.libraryproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long BookId;

	@Column(name = "PublisherId")
	private Long PublisherId;

	@Column(name = "AuthorId")
	private Long AuthorId;

	@Column(name = "BookName")
	private String BookName;

	@Column(name = "BookSubName")
	private String BookSubName;

	@Column(name = "BookSeriesName")
	private String BookSeriesName;

	@Column(name = "BookIsbnNo")
	private String BookIsbnNo;

	@Column(name = "BookLanguage")
	private String BookLanguage;

	@Column(name = "BookReleaseDate")
	private Date BookReleaseDate;

	@Column(name = "BookDefination")
	private String BookDefination;

	protected Books() {
	}

	public Long getBookId() {
		return BookId;
	}

	public void setBookId(Long bookId) {
		BookId = bookId;
	}

	public Long getPublisherId() {
		return PublisherId;
	}

	public void setPublisherId(Long publisherId) {
		PublisherId = publisherId;
	}

	public Long getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(Long authorId) {
		AuthorId = authorId;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookSubName() {
		return BookSubName;
	}

	public void setBookSubName(String bookSubName) {
		BookSubName = bookSubName;
	}

	public String getBookSeriesName() {
		return BookSeriesName;
	}

	public void setBookSeriesName(String bookSeriesName) {
		BookSeriesName = bookSeriesName;
	}

	public String getBookIsbnNo() {
		return BookIsbnNo;
	}

	public void setBookIsbnNo(String bookIsbnNo) {
		BookIsbnNo = bookIsbnNo;
	}

	public String getBookLanguage() {
		return BookLanguage;
	}

	public void setBookLanguage(String bookLanguage) {
		BookLanguage = bookLanguage;
	}

	public Date getBookReleaseDate() {
		return BookReleaseDate;
	}

	public void setBookReleaseDate(Date bookReleaseDate) {
		BookReleaseDate = bookReleaseDate;
	}

	public String getBookDefination() {
		return BookDefination;
	}

	public void setBookDefination(String bookDefination) {
		BookDefination = bookDefination;
	}

}
