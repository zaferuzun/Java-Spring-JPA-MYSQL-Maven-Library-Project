package com.zenontechnology.libraryproject.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

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
	private LocalDate BookReleaseDate;

	@Column(name = "BookDefination")
	private String BookDefination;

	@Column(name = "BookImage")
	private String BookImage;

	@ManyToOne(optional = false)
	@JoinColumn(name = "AuthorId", referencedColumnName = "AuthorId", insertable = false, updatable = false)
	private Authors authors;

	@ManyToOne(optional = false)
	@JoinColumn(name = "PublisherId", referencedColumnName = "PublisherId", insertable = false, updatable = false)
	private Publishers publishers;

	public Books() {
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

	public LocalDate getBookReleaseDate() {
		return BookReleaseDate;
	}

	public void setBookReleaseDate(LocalDate localDate) {
		BookReleaseDate = localDate;
	}

	public String getBookDefination() {
		return BookDefination;
	}

	public void setBookDefination(String bookDefination) {
		BookDefination = bookDefination;
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

		return "/images/books-photos/" + BookId + "/" + BookImage;
	}

	public Authors getAuthors() {
		return authors;
	}

	public void setAuthors(Authors authors) {
		this.authors = authors;
	}

	public Publishers getPublishers() {
		return publishers;
	}

	public void setPublishers(Publishers publishers) {
		this.publishers = publishers;
	}

}
