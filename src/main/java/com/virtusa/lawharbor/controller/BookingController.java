package com.virtusa.lawharbor.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.service.BookingServiceImpl;
import com.virtusa.lawharbor.service.LawyerServiceImpl;
import com.virtusa.lawharbor.service.UserService;

@RestController
@RequestMapping("/User")
public class BookingController {

	private static final Logger LOGGER = LogManager.getLogger(BookingController.class.getName());
	
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	@Autowired
	private UserService userService;
	@Autowired
	private LawyerServiceImpl law;
	
	

	@GetMapping("/requestAppointment/{email}/{useremail}")
	public ModelAndView requestAppointment(@PathVariable String email, @PathVariable String useremail) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("lawyer", law.findLawyerByEmail(email));
		mv.addObject("user", userService.getByEmail(useremail));
		mv.setViewName("requestAppointment");
		return mv;
	}

	@PostMapping("/booking/{lawyeremail}/{useremail}")
	public ModelAndView sendRequest(@PathVariable String lawyeremail, @PathVariable String useremail,
			@ModelAttribute BookingModel model) {
		BookingModel b = model;
		b.setClientDetail(userService.getByEmail(useremail));
		b.setLawyerDetail(law.findLawyerByEmail(lawyeremail));
		bookingServiceImpl.saveBooking(b);
		return new ModelAndView("redirect:/User/Booking/" + useremail);

	}

	@GetMapping("/Booking/{email}")
	public ModelAndView showUserBookings(@PathVariable String email) {
		ModelAndView m = new ModelAndView();
		m.addObject("UserEmail", email);
		m.addObject("BookingList", bookingServiceImpl.findAllByClientDetail(userService.getByEmail(email)));
		m.setViewName("UserBookingList");
		return m;

	}
	
	@GetMapping("/deletebooking/{userEmail}/{id}")
	public ModelAndView deleteUserBooking(@PathVariable String userEmail, @PathVariable int id) {
		BookingModel bookModel = bookingServiceImpl.getBookingById(id);
		try {
			if(bookModel!=null) {
				bookingServiceImpl.deleteBookingById(id);
			}
			
		}catch (Exception e) {
			LOGGER.warn("Error In Delete Booking Service :{0}",e);
		}
		
		return new ModelAndView("redirect:/User/Booking/"+userEmail);
	}

}
