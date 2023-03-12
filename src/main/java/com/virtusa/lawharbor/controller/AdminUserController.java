package com.virtusa.lawharbor.controller;

import java.util.List;

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
import com.virtusa.lawharbor.service.UserService;

@Controller
@RequestMapping("/Admin")
public class AdminUserController {
	private static final Logger lOGGER = LogManager.getLogger(AdminUserController.class.getName());
	private String redirectUserList = "redirect:/Admin/UserList/";
	private static final String ERROR="error";
	private static final String ADMIN_EMAIL="AdminEmail";
	private static final String REG_FORM="registerForm";

	@Autowired
	private UserService userService;

	@GetMapping("/UserList/{adminEmail}")
	public ModelAndView getAllUserList(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			List<User> userList = userService.getAllUser();
			mv.addObject("UserList", userList);
			mv.addObject(ADMIN_EMAIL, adminEmail);
			mv.setViewName("UserList");
			lOGGER.info("User List Displayed");
		} catch (Exception e) {
			lOGGER.warn("Error in getAllUser controller class ", e);
		}
		return mv;
	}

	@GetMapping("/UserRegPage/{adminEmail}")
	public ModelAndView registerEmployee(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(ADMIN_EMAIL, adminEmail);
		mv.setViewName(REG_FORM);
		return mv;
	}

	@PostMapping("/RegisterUser/{adminEmail}")
	public ModelAndView register(@Valid @ModelAttribute User user, BindingResult result,
			@PathVariable String adminEmail) {
		ModelAndView mv = new ModelAndView();
		String error = "";
		try {
			if (result.hasErrors()) {
				mv.addObject("user", user);
				mv.addObject(ADMIN_EMAIL, adminEmail);
				mv.setViewName(REG_FORM);
				return mv;
			} else {
				if (userService.checkUserByEmail(user.getEmail())) {
					error = "User already exists with this Email Id : " + user.getEmail();
					lOGGER.error(error);
					mv.addObject(ERROR,error );
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.addObject("user", user);
					mv.setViewName(REG_FORM);
					return mv;
				}
				if (user.getConfirmPassword().equals(user.getPassword())) {
					user.setRole("User");
					userService.saveUser(user);
					lOGGER.info("Data Saved Successfully ");
				} else {
					error = "password and confirm password  are not equal: ";
					lOGGER.error(error);
					mv.addObject(ERROR, error);
					mv.addObject("user", user);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(REG_FORM);
					return mv;
				}
			}
		} catch (Exception e) {
			lOGGER.warn("Error in register controller class ", e);
		}

		return new ModelAndView(this.redirectUserList + adminEmail);
	}

	// delete handler
	@GetMapping("/deleteUser/{adminEmail}/{userId}")
	public ModelAndView deleteUser(@PathVariable String adminEmail, @PathVariable int userId) {
		userService.deleteUser(userId);

		return new ModelAndView(this.redirectUserList + adminEmail);
	}

	@GetMapping("/updateUser/{adminEmail}/{userId}")
	public ModelAndView updateUserForm(@PathVariable String adminEmail, @PathVariable int userId, Model model) {
		User user = this.userService.getUserById(userId);
		System.out.println(user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject(ADMIN_EMAIL, adminEmail);
		mv.setViewName("UpdateForm");
		return mv;
	}

	@PostMapping("/updateUser/{adminEmail}")
	public ModelAndView updateUser(@PathVariable String adminEmail, @ModelAttribute User user, Model model) {
		ModelAndView mv = new ModelAndView();
		String error = "";
		try {
			if (user.getName().isBlank() || user.getConfirmPassword().isBlank() || user.getPhoneNo().isBlank()) {
				lOGGER.warn("Empty Fields.");
				return new ModelAndView("redirect:/Admin/updateUser/" + adminEmail+user.getId());
			}
			if(user.getPassword().equals(user.getConfirmPassword())) {
				userService.updateUser(user, user.getId());
				lOGGER.info("Data updated Successfully ");
			}else {
				error = "password and confirm password  are not equal: ";
				lOGGER.error(error);
				mv.addObject("user", user);
				mv.addObject(ERROR, error);
				mv.addObject(ADMIN_EMAIL, adminEmail);
				mv.setViewName(REG_FORM);
				return mv;
			}

		} catch (Exception e) {
			lOGGER.warn("Error in register controller class ", e);
		}
		return new ModelAndView(this.redirectUserList + adminEmail);
	}

}
