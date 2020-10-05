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
	private Long SenderCheck;

	@Column(name = "TargetCheck")
	private Long TargetCheck;

	@Column(name = "SwapStatus")
	private Long SwapStatus;

	@Column(name = "LocationMeetway")
	private Long LocationMeetway;
}
