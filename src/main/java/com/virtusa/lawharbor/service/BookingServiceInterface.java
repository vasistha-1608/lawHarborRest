package com.virtusa.lawharbor.service;

import java.util.List;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.LawyerModel;

public interface BookingServiceInterface {
	
	public BookingModel saveBooking(BookingModel booking);
	
	public List<BookingModel> getBookings();
	
	public List<BookingModel> findAllBylawyerDetail(LawyerModel lawyerDetail);
	
	public BookingModel getBookingById(int id);
	
	public void deleteBookingById(int id);
	
}
