package com.zenontechnology.libraryproject.dto;

import javax.persistence.Column;

public class UserSwapDto {

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
	// ***
	@Column(name = "SenderName")
	private String SenderName;

	@Column(name = "TargetName")
	private String TargetName;

	@Column(name = "SenderBookName")
	private String SenderBookName;

	@Column(name = "TargetBookName")
	private String TargetBookName;

	public UserSwapDto() {
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

	public String getSenderName() {
		return SenderName;
	}

	public void setSenderName(String senderName) {
		SenderName = senderName;
	}

	public String getTargetName() {
		return TargetName;
	}

	public void setTargetName(String targetName) {
		TargetName = targetName;
	}

	public String getSenderBookName() {
		return SenderBookName;
	}

	public void setSenderBookName(String senderBookName) {
		SenderBookName = senderBookName;
	}

	public String getTargetBookName() {
		return TargetBookName;
	}

	public void setTargetBookName(String targetBookName) {
		TargetBookName = targetBookName;
	}

}
