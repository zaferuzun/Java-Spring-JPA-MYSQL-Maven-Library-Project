package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookswap")
public class BookSwap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SwapId")
	private Long SwapId;

	@Column(name = "SenderId")
	private Long SenderId;

	@Column(name = "TargetId")
	private Long TargetId;

	@Column(name = "SenderBookId")
	private Long SenderBookId;

	@Column(name = "TargetBookId")
	private Long TargetBookId;

	@Column(name = "SenderCheck")
	private Boolean SenderCheck;

	@Column(name = "TargetCheck")
	private Boolean TargetCheck;

	@Column(name = "SwapStatus")
	private String SwapStatus;

	@Column(name = "LocationMeetway")
	private String LocationMeetway;

	public BookSwap() {
	}

	public Long getSwapId() {
		return SwapId;
	}

	public void setSwapId(Long swapId) {
		SwapId = swapId;
	}

	public Long getSenderId() {
		return SenderId;
	}

	public void setSenderId(Long senderId) {
		SenderId = senderId;
	}

	public Long getTargetId() {
		return TargetId;
	}

	public void setTargetId(Long targetId) {
		TargetId = targetId;
	}

	public Long getSenderBookId() {
		return SenderBookId;
	}

	public void setSenderBookId(Long senderBookId) {
		SenderBookId = senderBookId;
	}

	public Long getTargetBookId() {
		return TargetBookId;
	}

	public void setTargetBookId(Long targetBookId) {
		TargetBookId = targetBookId;
	}

	public Boolean getSenderCheck() {
		return SenderCheck;
	}

	public void setSenderCheck(Boolean senderCheck) {
		SenderCheck = senderCheck;
	}

	public Boolean getTargetCheck() {
		return TargetCheck;
	}

	public void setTargetCheck(Boolean targetCheck) {
		TargetCheck = targetCheck;
	}

	public String getSwapStatus() {
		return SwapStatus;
	}

	public void setSwapStatus(String swapStatus) {
		SwapStatus = swapStatus;
	}

	public String getLocationMeetway() {
		return LocationMeetway;
	}

	public void setLocationMeetway(String locationMeetway) {
		LocationMeetway = locationMeetway;
	}

}
