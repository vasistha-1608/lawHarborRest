package com.virtusa.lawharbor.service;

import com.virtusa.lawharbor.model.AdminModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

public interface LoginAndSignupInterface {
	public User checkUser(String email, String password);
	public LawyerModel checkLawyer(String email, String password);
	public AdminModel checkAdmin(String email, String password);
}
