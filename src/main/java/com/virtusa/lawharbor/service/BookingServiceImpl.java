package com.virtusa.lawharbor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.BookingDao;
import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

@Service
public class BookingServiceImpl implements BookingServiceInterface {

	@Autowired
	private BookingDao bookingDao;
	@Autowired
	CaseRecordServiceImpl caseRecordServiceImpl;

	@Override
	public BookingModel saveBooking(BookingModel booking) {

		return this.bookingDao.save(booking);
	}

	@Override
	public List<BookingModel> getBookings() {

		return this.bookingDao.findAll();
	}

	@Override
	public List<BookingModel> findAllBylawyerDetail(LawyerModel lawyerDetail) {

		return this.bookingDao.findAllByLawyerDetail(lawyerDetail);
	}

	@Override
	public BookingModel getBookingById(int id) {

		return this.bookingDao.findById(id).get();
	}

	public void deleteBookingById(int id) {

		if (this.bookingDao.existsById(id)) {
			BookingModel bookingModel = this.getBookingById(id);
			if (caseRecordServiceImpl.existsCaseRecordByBookingId(bookingModel)) {
				caseRecordServiceImpl.deleteCaseRecordByBookingDetails(bookingModel);
			}
			this.bookingDao.deleteById(id);
		}
	}

	public Object findAllByClientDetail(User byEmail) {

		return this.bookingDao.findAllByClientDetail(byEmail);
	}

}
