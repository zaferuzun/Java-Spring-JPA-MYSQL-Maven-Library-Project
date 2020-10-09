package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CommentId;

	@Column(name = "UserId")
	private Long UserId;

	@Column(name = "BookId")
	private Long BookId;

	@Column(name = "PublisherId")
	private Long PublisherId;

	@Column(name = "AuthorId")
	private Long AuthorId;

	@Column(name = "UserBookId")
	private Long UserBookId;

	@Column(name = "AdminCheck")
	private Boolean AdminCheck;

	@Column(name = "CommentDetails")
	private String CommentDetails;

	@ManyToOne(optional = false)
	@JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
	private Users users;

	public Comments() {
	}

	public Long getCommentId() {
		return CommentId;
	}

	public void setCommentId(Long commentId) {
		CommentId = commentId;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
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

	public Long getUserBookId() {
		return UserBookId;
	}

	public void setUserBookId(Long userBookId) {
		UserBookId = userBookId;
	}

	public Boolean getAdminCheck() {
		return AdminCheck;
	}

	public void setAdminCheck(Boolean adminCheck) {
		AdminCheck = adminCheck;
	}

	public String getCommentDetails() {
		return CommentDetails;
	}

	public void setCommentDetails(String commentDetails) {
		CommentDetails = commentDetails;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
