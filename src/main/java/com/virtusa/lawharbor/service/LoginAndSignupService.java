package com.virtusa.lawharbor.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.AdminDao;
import com.virtusa.lawharbor.dao.LawyerDao;
import com.virtusa.lawharbor.dao.UserDao;
import com.virtusa.lawharbor.model.AdminModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

@Service
public class LoginAndSignupService implements LoginAndSignupInterface{
	private static final Logger lOGGER = LogManager.getLogger(LoginAndSignupService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private LawyerDao lawyerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public User checkUser(String email, String password) {
		try {
			return userDao.findByEmailAndPassword(email, password);
		}catch (Exception e) {
			lOGGER.error("Error in Check User ");
		}
		return null;
	}

	@Override
	public LawyerModel checkLawyer(String email, String password) {
		try {
			return lawyerDao.findByEmailAndPassword(email, password);
		}catch (Exception e) {
			lOGGER.error("Error in Check User ");
		}
		return null;
	}

	@Override
	public AdminModel checkAdmin(String email, String password) {

		try {
			return adminDao.findByEmailAndPassword(email, password);
		}catch (Exception e) {
			lOGGER.error("Error in check Admin ");
		}
		return null;
	}
	
	

}
