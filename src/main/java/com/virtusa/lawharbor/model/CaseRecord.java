package com.virtusa.lawharbor.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class CaseRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int caseRecordId;
	@OneToOne
	private User user;
	@OneToOne
	private BookingModel bookingDetails;
	private Date date;
	private String eventDetails;
	private String actionTaken;
	@OneToOne
	private LawyerModel issuedBy;
	public int getCaseRecordId() {
		return caseRecordId;
	}
	public void setCaseRecordId(int caseRecordId) {
		this.caseRecordId = caseRecordId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BookingModel getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(BookingModel bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	public LawyerModel getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(LawyerModel lawyerModel) {
		this.issuedBy = lawyerModel;
	}
	public CaseRecord(int caseRecordId, User user, BookingModel bookingDetails, Date date, String eventDetails,
			String actionTaken, LawyerModel issuedBy) {
		super();
		this.caseRecordId = caseRecordId;
		this.user = user;
		this.bookingDetails = bookingDetails;
		this.date = date;
		this.eventDetails = eventDetails;
		this.actionTaken = actionTaken;
		this.issuedBy = issuedBy;
	}
	public CaseRecord() {
		super();

	}
	@Override
	public String toString() {
		return "CaseRecord [caseRecordId=" + caseRecordId + ", user=" + user.getName() + ", bookingDetails=" + bookingDetails.getBookingId()
				+ ", date=" + date + ", eventDetails=" + eventDetails + ", actionTaken=" + actionTaken + ", issuedBy="
				+ issuedBy.getName() + "]";
	}

	
}
