package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "authors")
public class Authors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AuthorId", nullable = false)
	private Long AuthorId;

	@Column(name = "AuthorName")
	private String AuthorName;

	@Column(name = "AuthorSurname")
	private String AuthorSurname;

	@Column(name = "AuthorContact")
	private String AuthorContact;

	@Column(name = "AuthorDefination")
	private String AuthorDefination;

	@Column(name = "AuthorImage")
	private String AuthorImage;

	public Authors() {
	}

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

	public String getAuthorImage() {
		return AuthorImage;
	}

	public void setAuthorImage(String authorImage) {
		AuthorImage = authorImage;
	}

	@Transient
	public String getAuthorsPhotosImagePath() {
		if (AuthorImage == null || AuthorId == null)
			return null;

		return "/images/authors-photos/" + AuthorId + "/" + AuthorImage;
	}
}
