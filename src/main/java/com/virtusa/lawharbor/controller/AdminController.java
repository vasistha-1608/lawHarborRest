package com.virtusa.lawharbor.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.lawharbor.model.AdminModel;
import com.virtusa.lawharbor.service.AdminServiceImpl;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	private static final Logger lOGGER = LogManager.getLogger(AdminController.class.getName());
	private String redirectUserList = "redirect:/Admin/AdminList/";
	
	private static final String ADMIN_EMAIL="AdminEmail";
	private static final String ADMIN_FORM="AdminRegForm";
	private static final String ADMIN_OBJ = "admin";
	

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@GetMapping("/home/{email}")
	public ModelAndView getHomePage(@PathVariable String email, Model model) {
		ModelAndView view = new ModelAndView();
		view.addObject(ADMIN_EMAIL, email);
		System.out.println(email);
		view.setViewName("AdminHome");
		return view;
	}

	@GetMapping("/AdminList/{adminEmail}")
	public ModelAndView getAllUserList(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			List<AdminModel> adminList = adminServiceImpl.getAllAdmin();
			mv.addObject("adminList", adminList);
			mv.addObject(ADMIN_EMAIL, adminEmail);
			mv.setViewName("AdminList");
			lOGGER.info("Admin List Displayed");
		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in getAllUser controller class ", e);
		}
		return mv;
	}

	@GetMapping("/AdminRegPage/{adminEmail}")
	public ModelAndView registerEmployee(@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(ADMIN_EMAIL, adminEmail);
		mv.setViewName(ADMIN_FORM);
		return mv;
	}

	@PostMapping("/AdminRegister/{adminEmail}")
	public ModelAndView register(@Valid @ModelAttribute AdminModel admin, BindingResult result,
			@PathVariable String adminEmail, Model model) {
		ModelAndView mv = new ModelAndView();
		String error = "";
		try {

			if (result.hasErrors()) {
				mv.addObject(ADMIN_OBJ, admin);
				mv.addObject(ADMIN_EMAIL, adminEmail);
				mv.setViewName(ADMIN_FORM);
				return mv;
			} else {

				if (adminServiceImpl.existsAdminByEmail(admin.getEmail())) {
					error = "Admin already exists with this Email Id : " + admin.getEmail();
					lOGGER.error(error);
					mv.addObject("error", error);
					mv.addObject(ADMIN_OBJ, admin);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(ADMIN_FORM);
					return mv;
				}
				if (admin.getConfirmPassword().equals(admin.getPassword())) {
					admin.setRole("Admin");
					adminServiceImpl.saveAdmin(admin);
					lOGGER.log(Level.INFO, "Data Saved Successfully , :{0}", admin);
				} else {
					error="Password did not match with confirm password.";
					mv.addObject("error", error);
					mv.addObject(ADMIN_OBJ, admin);
					mv.addObject(ADMIN_EMAIL, adminEmail);
					mv.setViewName(ADMIN_FORM);
					return mv;
				}

			}

		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in register controller class ", e);
		}

		return new ModelAndView(this.redirectUserList + adminEmail);
	}

	@GetMapping("/updateAdmin/{adminId}")
	public ModelAndView updateAdminForm(@PathVariable int adminId, Model model) {
		AdminModel admin = this.adminServiceImpl.getAdminById(adminId);
		ModelAndView mv = new ModelAndView();
		mv.addObject(ADMIN_OBJ, admin);
		mv.setViewName("AdminUpdateForm");
		return mv;
	}

	@PostMapping("/updateAdmin")
	public ModelAndView updateAdmin(@Valid @ModelAttribute AdminModel adminModel, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			if (result.hasErrors()) {
				mv.addObject(ADMIN_OBJ, adminModel);
				mv.setViewName("AdminUpdateForm");
				return mv;
			}

			adminServiceImpl.updateAdmin(adminModel, adminModel.getId());

		} catch (Exception e) {
			lOGGER.log(Level.WARN, "Error in Update Admin controller class ", e);
		}
		return new ModelAndView(this.redirectUserList);
	}

}
