package com.virtusa.lawharbor.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.service.LawyerServiceImpl;

@Controller
@RequestMapping("/Admin")
public class AdminLawyerController {
	private static final Logger lOGGER = LogManager.getLogger(AdminLawyerController.class.getName());
	private String redirectLawyerList = "redirect:/Admin/LawyerList/";

	@Autowired
	private LawyerServiceImpl lawyerServiceImpl;
	
	private static final String ADMIN_EMAIL="AdminEmail";
	private static final String LAWYER_MODEL="lawyer";
	private static final String LAWYER_REG_FORM="LawyerRegForm";
	private static final String LAWYER_UPDATE_FORM="LawyerUpdateForm";
	private static final String ERROR="error";

	@GetMapping("/LawyerList/{adminEmail}")
	public ModelAndView getAllLawyerList(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			List<LawyerModel> lawyerList = lawyerServiceImpl.getLawyers();
			mv.addObject("LawyerList", lawyerList);
			mv.addObject(ADMIN_EMAIL, adminEmail);
			mv.setViewName("LawyerList");
			lOGGER.info("Lawyer List Displayed");
		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in getAllLawyer controller class ", e);
		}
		return mv;
	}

	@GetMapping("/LawyerRegPage/{adminEmail}")
	public ModelAndView registerLawyer(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(ADMIN_EMAIL, adminEmail);
		mv.setViewName(LAWYER_REG_FORM);
		return mv;
	}

	@PostMapping("/LawyerRegister/{adminEmail}")
	public ModelAndView lawyerRegister(@Valid @ModelAttribute LawyerModel lawyer, BindingResult result,
			@PathVariable String adminEmail) {

		ModelAndView mv = new ModelAndView();
		String error = "";

		try {
			if (result.hasErrors()) {
				mv.addObject(LAWYER_MODEL, lawyer);
				mv.addObject(ADMIN_EMAIL, adminEmail);
				mv.setViewName(LAWYER_REG_FORM);
				return mv;
			} else {
				if (lawyerServiceImpl.existsLawyerByEmail(lawyer.getEmail())) {
					error = "Lawyer already exists with this Email Id : " + lawyer.getEmail();
					lOGGER.error(error);
					mv.addObject(ERROR, error);
					mv.addObject(LAWYER_MODEL, lawyer);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(LAWYER_REG_FORM);
					return mv;
				}
				if (lawyer.getConfirmPassword().equals(lawyer.getPassword())) {
					lawyer.setRole("Lawyer");
					lawyerServiceImpl.saveLawyer(lawyer);
					lOGGER.log(Level.INFO, "Data Saved Successfully , :{0}", lawyer);
				} else {
					error = "password and confirm password  are not equal: ";
					lOGGER.error(error);
					mv.addObject(LAWYER_MODEL, lawyer);
					mv.addObject(ERROR, error);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(LAWYER_REG_FORM);
					return mv;
				}
			}
		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in register controller class ", e);
		}

		return new ModelAndView(this.redirectLawyerList + adminEmail);
	}

	// delete handler
	@GetMapping("/deleteLawyer/{adminEmail}/{lawyerId}")
	public ModelAndView deleteLawyer(@PathVariable String adminEmail, @PathVariable int lawyerId) {
		lawyerServiceImpl.deleteLawyer(lawyerId);

		return new ModelAndView(this.redirectLawyerList + adminEmail);
	}

	@GetMapping("/updateLawyer/{adminEmail}/{lawyerId}")
	public ModelAndView updateLawyerForm(@PathVariable String adminEmail, @PathVariable int lawyerId, Model model) {
		LawyerModel lawyer = this.lawyerServiceImpl.findLawyerById(lawyerId);
		System.out.println(lawyer);
		ModelAndView mv = new ModelAndView();
		mv.addObject(ADMIN_EMAIL, adminEmail);
		mv.addObject(LAWYER_MODEL, lawyer);
		mv.setViewName(LAWYER_UPDATE_FORM);
		return mv;
	}

	@PostMapping("/updateLawyer/{adminEmail}")
	public ModelAndView updateLawyer(@Valid @ModelAttribute LawyerModel lawyer, BindingResult result, Model model,
			@PathVariable String adminEmail) {
		ModelAndView mv = new ModelAndView();
		String error = "";
		try {
			if (result.hasErrors()) {
				mv.addObject(LAWYER_MODEL, lawyer);
				mv.addObject(ADMIN_EMAIL, adminEmail);
				mv.setViewName(LAWYER_UPDATE_FORM);
				return mv;
			} else {
				if (lawyer.getConfirmPassword().equals(lawyer.getPassword())) {
					lawyerServiceImpl.updateLawyer(lawyer, lawyer.getId());
					lOGGER.info("Data updated Successfully ");
				} else {
					error = "password and confirm password  are not equal: ";
					lOGGER.error(error);
					mv.addObject(LAWYER_MODEL, lawyer);
					mv.addObject(ERROR, error);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(LAWYER_UPDATE_FORM);
					return mv;
				}
			}
		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in register controller class ", e);
		}
		return new ModelAndView(this.redirectLawyerList + adminEmail);
	}
}
