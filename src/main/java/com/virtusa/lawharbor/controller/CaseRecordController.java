package com.virtusa.lawharbor.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.CaseRecord;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;
import com.virtusa.lawharbor.service.BookingServiceImpl;
import com.virtusa.lawharbor.service.CaseRecordServiceImpl;
import com.virtusa.lawharbor.service.LawyerServiceImpl;
import com.virtusa.lawharbor.service.UserService;

@Controller
@RequestMapping("/CaseRecord")
public class CaseRecordController {

	private static final Logger LOGGER = LogManager.getLogger(CaseRecordController.class.getName());
	private static final String REDIRECT_VIEW_CASERECORD = "redirect:/CaseRecord/viewCaseRecords/";
	private static final String REDIRECT_UPDATE_CASERECORD = "redirect:/CaseRecord/usercaserecord/";
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	@Autowired
	private CaseRecordServiceImpl caseRecordServiceImpl;

	@Autowired
	private UserService userService;

	@Autowired
	private LawyerServiceImpl lawyerServiceImpl;

	@GetMapping("/CaseRecordForm/{bookingId}")
	public ModelAndView viewCaseRecordForm(@PathVariable int bookingId) {
		ModelAndView mv = new ModelAndView();
		BookingModel booking = bookingServiceImpl.getBookingById(bookingId);
		mv.addObject("booking", booking);
		mv.setViewName("CaseRecordForm");
		return mv;
	}

	@PostMapping("/addCaseRecord/{id}")
	public ModelAndView addCaseRecord(@PathVariable int id, @ModelAttribute CaseRecord caseRecord) {
		BookingModel booking = bookingServiceImpl.getBookingById(id);
		caseRecord.setUser(booking.getClientDetail());
		caseRecord.setIssuedBy(booking.getLawyerDetail());
		caseRecord.setBookingDetails(booking);

		if (this.caseRecordServiceImpl.existsCaseRecordByBookingId(booking)) {
			LOGGER.warn("Case Record Already Exists");
		} else {
			this.caseRecordServiceImpl.saveCaseRecord(caseRecord);
		}
		return new ModelAndView( REDIRECT_VIEW_CASERECORD+ booking.getLawyerDetail().getId());
	}

	@GetMapping("/viewCaseRecords/{lawyerid}")
	public ModelAndView getRecordsByLawyer(@PathVariable int lawyerid) {
		ModelAndView mv = new ModelAndView();
		LawyerModel lawyer = lawyerServiceImpl.findLawyerById(lawyerid);
		mv.addObject("RecordList", this.caseRecordServiceImpl.getAllCaseRecordsByIssuedBy(lawyer));
		mv.addObject("UserId", lawyerid);
		mv.setViewName("ViewCaseRecords");
		return mv;
	}

	@GetMapping("/usercaserecord/{email}")
	public ModelAndView getRecordsByUser(@PathVariable String email) {
		ModelAndView mv = new ModelAndView();
		User user = userService.getByEmail(email);
		mv.addObject("RecordList", this.caseRecordServiceImpl.getAllCaseRecordByUser(user));
		mv.addObject("UserEmail", email);
		mv.setViewName("ViewUserCaseRecords");
		return mv;
	}

	@GetMapping("/deleteRecord/{caseId}")
	public ModelAndView deleteLawyerCaseRecord(@PathVariable int caseId) {
		CaseRecord cs = this.caseRecordServiceImpl.deleteCaseRecordById(caseId);
		return new ModelAndView(REDIRECT_VIEW_CASERECORD + cs.getIssuedBy().getId());
	}

	@GetMapping("/deleteUserRecord/{caseId}")
	public ModelAndView deleteUserCaseRecord(@PathVariable int caseId) {
		CaseRecord cs = this.caseRecordServiceImpl.deleteCaseRecordById(caseId);
		return new ModelAndView(REDIRECT_UPDATE_CASERECORD + cs.getUser().getEmail());
	}

	@GetMapping("/displayUpdateCaseRecord/{caseId}")
	public ModelAndView getUpdateCaseRecord(@PathVariable int caseId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("UpdateCaseRecord");
		mv.addObject("CaseRecord", this.caseRecordServiceImpl.findCaseRecordById(caseId));
		return mv;
	}

	@PostMapping("/updateCaseRecord/{id}")
	public ModelAndView updateCaseReport(@PathVariable int id, @ModelAttribute CaseRecord caseRecord) {

		CaseRecord cs = this.caseRecordServiceImpl.updateCaseRecord(caseRecord);
		if (cs == null) {
			return new ModelAndView(REDIRECT_UPDATE_CASERECORD+ id);
		}
		return new ModelAndView(REDIRECT_VIEW_CASERECORD + cs.getIssuedBy().getId());
	}

}
