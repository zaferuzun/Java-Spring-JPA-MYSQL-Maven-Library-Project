package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "publishers")
public class Publishers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PublisherId;

	@Column(name = "PublisherName")
	private String PublisherName;

	@Column(name = "PublisherContact")
	private String PublisherContact;

	@Column(name = "PublisherDefination")
	private String PublisherDefination;

	@Column(name = "PublisherImage")
	private String PublisherImage;

	public Publishers() {

	}

	public Long getPublisherId() {
		return PublisherId;
	}

	public void setPublisherId(Long publisherId) {
		PublisherId = publisherId;
	}

	public String getPublisherName() {
		return PublisherName;
	}

	public void setPublisherName(String publisherName) {
		PublisherName = publisherName;
	}

	public String getPublisherContact() {
		return PublisherContact;
	}

	public void setPublisherContact(String publisherContact) {
		PublisherContact = publisherContact;
	}

	public String getPublisherDefination() {
		return PublisherDefination;
	}

	public void setPublisherDefination(String publisherDefination) {
		PublisherDefination = publisherDefination;
	}

	public String getPublisherImage() {
		return PublisherImage;
	}

	public void setPublisherImage(String publisherImage) {
		PublisherImage = publisherImage;
	}

	@Transient
	public String getPublishersPhotosImagePath() {
		if (PublisherImage == null || PublisherId == null)
			return null;

		return "/images/publishers-photos/" + PublisherId + "/" + PublisherImage;
	}
}
