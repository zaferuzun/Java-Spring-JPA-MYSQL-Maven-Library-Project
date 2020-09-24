package com.zenontechnology.libraryproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authors {

	private Long AuthorId;
	private String AuthorName;
	private String AuthorSurname;
	private String AuthorContact;
	private String AuthorDefination;

	protected Authors() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(Long authorId) {
		AuthorId = authorId;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}

	public String getAuthorSurname() {
		return AuthorSurname;
	}

	public void setAuthorSurname(String authorSurname) {
		AuthorSurname = authorSurname;
	}

	public String getAuthorContact() {
		return AuthorContact;
	}

	public void setAuthorContact(String authorContact) {
		AuthorContact = authorContact;
	}

	public String getAuthorDefination() {
		return AuthorDefination;
	}

	public void setAuthorDefination(String authorDefination) {
		AuthorDefination = authorDefination;
	}

}
