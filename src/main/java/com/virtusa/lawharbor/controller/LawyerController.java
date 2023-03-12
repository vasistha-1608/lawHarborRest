package com.virtusa.lawharbor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.service.BookingServiceImpl;
import com.virtusa.lawharbor.service.LawyerServiceImpl;

@Controller
@RequestMapping("/Lawyer")
public class LawyerController {
	
	private static final String REDIRECT_LAWYER_BOOKING = "redirect:/Lawyer/Booking/";
	
	
	@Autowired
	private BookingServiceImpl bookingService;
	@Autowired
	private LawyerServiceImpl lawyerServiceImpl;

	@GetMapping("/home/{id}")
	public ModelAndView getLawyerHome(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		LawyerModel lawyer = lawyerServiceImpl.findLawyerById(id);
		if(lawyer.isActive()) {
			mv.addObject("Lawyer",lawyer);
			mv.addObject("UserId", id);
			mv.setViewName("LawyerHome");
			return mv;
		}
		mv.setViewName("redirect:/LawyerLogout/"+id);
		return mv;
	}

	@GetMapping("/Booking/{id}")
	public ModelAndView getBookings(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		LawyerModel lawyer = lawyerServiceImpl.findLawyerById(id);
		List<BookingModel> bookingList = bookingService.findAllBylawyerDetail(lawyer);
		mv.addObject("BookingList", bookingList);
		mv.addObject("UserId", id);
		mv.setViewName("ViewBookingList");
		return mv;
	}

	@GetMapping("accept/{bookingId}")
	public ModelAndView acceptBooking(@PathVariable int bookingId) {
		BookingModel booking = this.bookingService.getBookingById(bookingId);
		booking.setBookingStatus(true);
		bookingService.saveBooking(booking);
		return new ModelAndView(REDIRECT_LAWYER_BOOKING + booking.getLawyerDetail().getId());
	}

	@GetMapping("reject/{bookingId}")
	public ModelAndView rejectBooking(@PathVariable int bookingId) {
		BookingModel booking = this.bookingService.getBookingById(bookingId);
		booking.setBookingStatus(false);
		bookingService.saveBooking(booking);
		return new ModelAndView(REDIRECT_LAWYER_BOOKING + booking.getLawyerDetail().getId());
	}

	@GetMapping("/delete/{bookingId}")
	public ModelAndView deleteBooking(@PathVariable int bookingId) {
		BookingModel booking = this.bookingService.getBookingById(bookingId);
		bookingService.deleteBookingById(bookingId);
		return new ModelAndView(REDIRECT_LAWYER_BOOKING+ booking.getLawyerDetail().getId());
	}

}
