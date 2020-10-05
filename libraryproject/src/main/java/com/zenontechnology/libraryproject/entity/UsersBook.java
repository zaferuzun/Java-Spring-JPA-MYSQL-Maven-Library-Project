package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersbook")
public class UsersBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserBookId;

	@Column(name = "UserId")
	private Long UserId;

	@Column(name = "UserBookName")
	private String UserBookName;

	@Column(name = "UserBookAuthor")
	private String UserBookAuthor;

	@Column(name = "UserBookPublisher")
	private String UserBookPublisher;

	@Column(name = "UserAddress")
	private String UserAddress;

	@Column(name = "UserBookImage")
	private String UserBookImage;

	@Column(name = "UserBookDefination")
	private String UserBookDefination;

	public UsersBook() {

	}

	public Long getUserBookId() {
		return UserBookId;
	}

	public void setUserBookId(Long userBookId) {
		UserBookId = userBookId;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getUserBookName() {
		return UserBookName;
	}

	public void setUserBookName(String userBookName) {
		UserBookName = userBookName;
	}

	public String getUserBookAuthor() {
		return UserBookAuthor;
	}

	public void setUserBookAuthor(String userBookAuthor) {
		UserBookAuthor = userBookAuthor;
	}

	public String getUserBookPublisher() {
		return UserBookPublisher;
	}

	public void setUserBookPublisher(String userBookPublisher) {
		UserBookPublisher = userBookPublisher;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public String getUserBookImage() {
		return UserBookImage;
	}

	public void setUserBookImage(String userBookImage) {
		UserBookImage = userBookImage;
	}

	public String getUserBookDefination() {
		return UserBookDefination;
	}

	public void setUserBookDefination(String userBookDefination) {
		UserBookDefination = userBookDefination;
	}

}
