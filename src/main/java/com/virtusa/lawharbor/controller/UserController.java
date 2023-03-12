package com.virtusa.lawharbor.controller;


import javax.validation.Valid;

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

import com.virtusa.lawharbor.model.User;
import com.virtusa.lawharbor.service.LawyerServiceImpl;
import com.virtusa.lawharbor.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {
	
	private static final Logger lOGGER = LogManager.getLogger(UserController.class.getName());

	@Autowired
	private LawyerServiceImpl lawyerServiceImpl;
	
	@Autowired
	private UserService userService;

	@GetMapping("/home/{email}")
	public ModelAndView home(@PathVariable String email , Model model) {
		ModelAndView mv = new ModelAndView();
		User user = userService.getByEmail(email);
		if( user==null || !user.isActive()) {
			mv.setViewName("redirect:/UserLogout/"+email);
			return mv;
		}
		mv.setViewName("UserHome");
		mv.addObject("UserEmail", email);
		return mv;
	}
	
	  @GetMapping("/getLawyers/{email}")
		public ModelAndView getLawyer(@PathVariable String email) {
	     	
	     ModelAndView mv = new ModelAndView();
	      mv.addObject("UserEmail", email); 
	      mv.addObject("LawyerList", lawyerServiceImpl.getLawyers());
	      mv.setViewName("UserLawyerList");
		  return mv;
	  }
	  
		@GetMapping("/updateUser/{userEmail}")
		public ModelAndView updateUserForm( @PathVariable String userEmail,Model model) {
			User user = this.userService.getByEmail(userEmail);
			ModelAndView mv = new ModelAndView();
			if(user!=null) {
				mv.addObject("user", user);
				mv.setViewName("UpdateForm");
				return mv;
			}
			return mv;
		}

		@PostMapping("/updateUser/{UserEmail}")
		public ModelAndView updateUser(@Valid @ModelAttribute User user,BindingResult result, @PathVariable String userEmail, Model model) {
			try {
				ModelAndView mv = new ModelAndView();
				
				if(result.hasErrors()) {
					mv.addObject(user);
					mv.setViewName("UpdateForm");
					return mv;
				}
				userService.updateUser(user, user.getId());

			} catch (Exception e) {
				lOGGER.warn("Error in register controller class ", e);
			}
			return new ModelAndView();
		}
	
}
