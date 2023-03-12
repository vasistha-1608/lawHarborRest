package com.virtusa.lawharbor.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.AdminModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;
import com.virtusa.lawharbor.service.AdminServiceImpl;
import com.virtusa.lawharbor.service.LawyerServiceImpl;
import com.virtusa.lawharbor.service.LoginAndSignupService;
import com.virtusa.lawharbor.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	private static final String REGISTER_FORM = "UserRegisterForm";
	private static final String LOGIN_FORM = "Login";
	private static final String REDIRECT_LOGIN_FORM = "redirect:/Login";
	private static final String ERROR="error";

	@Autowired
	private UserService userService;
	@Autowired
	private LoginAndSignupService loginSignupService;
	@Autowired
	private LawyerServiceImpl lawyerServiceImpl;
	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@GetMapping("/Login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(LOGIN_FORM);
		return mv;
	}

	@PostMapping("/getLogin")
	public ModelAndView getLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("role") String role) {
		ModelAndView mv = new ModelAndView();
		User user;
		AdminModel admin;
		LawyerModel lawyer;

		if (role.equals("User")) {
			user = loginSignupService.checkUser(email, password);
			if (user != null) {
				userService.updateUserActiveByEmail(email, true);
				System.out.println(user);
				return new ModelAndView("redirect:/User/home/" + user.getEmail());
			}
		}
		if (role.equals("Lawyer")) {
			lawyer = loginSignupService.checkLawyer(email, password);
			if (lawyer != null) {
				lawyerServiceImpl.updateLawyerActiveByEmail(email, true);
				return new ModelAndView("redirect:/Lawyer/home/" + lawyer.getId());
			}
		}
		if (role.equals("Admin")) {
			admin = loginSignupService.checkAdmin(email, password);
			if (admin != null) {
				adminServiceImpl.updateAdminActiveByEmail(email, true);
				return new ModelAndView("redirect:/Admin/home/" + admin.getEmail());
			}
		}
		LOGGER.error("invalid credentials");
		mv.addObject("email", email);
		mv.addObject("password", password);
		mv.addObject(ERROR, "invalid credeltials");
		mv.setViewName(LOGIN_FORM);
		return mv;

	}

	@GetMapping("/AdminLogout/{email}")
	public ModelAndView adminlogout(@PathVariable String email) {
		AdminModel admin = adminServiceImpl.getByEmail(email);
		if (admin.isActive()) {
			adminServiceImpl.updateAdminActiveByEmail(admin.getEmail(), false);
			LOGGER.log(Level.INFO, "Admin Logged out with email id :{0} ", admin.getEmail());
		}

		return new ModelAndView(REDIRECT_LOGIN_FORM);
	}

	@GetMapping("/LawyerLogout/{id}")
	public ModelAndView lawyerLogout(@PathVariable int id) {
		LawyerModel lawyer = lawyerServiceImpl.findLawyerById(id);
		if (lawyer.isActive()) {
			lawyerServiceImpl.updateLawyerActiveByEmail(lawyer.getEmail(), false);
			LOGGER.log(Level.INFO, "Lawyer Logged out with email id :{0} ", lawyer.getEmail());
		}
		return new ModelAndView(REDIRECT_LOGIN_FORM);
	}

	@GetMapping("/UserLogout/{email}")
	public ModelAndView userLogout(@PathVariable String email) {
		User user = userService.getByEmail(email);
		if (user.isActive()) {
			userService.updateUserActiveByEmail(user.getEmail(), false);
			LOGGER.log(Level.INFO, "User Logged out with email id :{0} ", user.getEmail());
		}
		return new ModelAndView(REDIRECT_LOGIN_FORM);
	}

	@GetMapping("/registerForm")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(REGISTER_FORM);
		return mv;
	}

	@PostMapping("/getregisterForm")
	public ModelAndView getregisterForm(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView();
		String error = "";
		if (result.hasErrors()) {
			mv.addObject("user", user);
			mv.setViewName(REGISTER_FORM);
			return mv;
		} else {

			if (userService.checkUserByEmail(user.getEmail())) {
				error = "User already exists with this Email Id : " + user.getEmail();
				LOGGER.error(error);
				mv.addObject(ERROR, error);
				mv.setViewName(REGISTER_FORM);
				return mv;
			}

			if (!(user.getConfirmPassword().equals(user.getPassword()))) {
				error = "password and confirm password  are not equal: ";
				LOGGER.error(error);
				mv.addObject(ERROR, error);
				mv.setViewName(REGISTER_FORM);
				return mv;
			}

			user.setRole("User");

			User u = userService.saveUser(user);
			if (u == null) {
				error = "User Registration was Unsuccessful with Email id: " + user.getEmail();
				LOGGER.error(error);
				mv.addObject(ERROR, error);
				mv.setViewName(REGISTER_FORM);
				return mv;
			} else {
				mv.addObject("msg", "Registration Success");
				mv.addObject("email", user.getEmail());
				mv.addObject("password", user.getPassword());
				mv.setViewName(LOGIN_FORM);
				return mv;
			}
		}
	}

}
