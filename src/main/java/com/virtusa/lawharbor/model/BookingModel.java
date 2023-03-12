package com.virtusa.lawharbor.model;




import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BookingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@OneToOne
	private User clientDetail;
	@OneToOne
	private LawyerModel lawyerDetail;
	private String lawfirmName;
	private Date date;
	private String time;
	private Boolean bookingStatus;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public User getClientDetail() {
		return clientDetail;
	}

	public void setClientDetail(User clientDetail) {
		this.clientDetail = clientDetail;
	}

	public LawyerModel getLawyerDetail() {
		return lawyerDetail;
	}

	public void setLawyerDetail(LawyerModel lawyerModel) {
		this.lawyerDetail = lawyerModel;
	}

	public String getLawfirmName() {
		return lawfirmName;
	}

	public void setLawfirmName(String lawfirmName) {
		this.lawfirmName = lawfirmName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}



	public BookingModel(int bookingId, User clientDetail, LawyerModel lawyerDetail, String lawfirmName, Date date, String time,
			Boolean bookingStatus) {
		super();
		this.bookingId = bookingId;
		this.clientDetail = clientDetail;
		this.lawyerDetail = lawyerDetail;
		this.lawfirmName = lawfirmName;
		this.date = date;
		this.time = time;
		this.bookingStatus = bookingStatus;
	}

	public BookingModel() {
		super();

	}

	@Override
	public String toString() {
		return "BookingModel [bookingId=" + bookingId + ", clientDetail=" + clientDetail.getName() + ", lawyerDetail="
				+ lawyerDetail.getName() + ", lawfirmName=" + lawfirmName + ", date=" + date + ", time=" + time
				+ ", bookingStatus=" + bookingStatus + "]";
	}


}
