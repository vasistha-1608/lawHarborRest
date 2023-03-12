package com.virtusa.lawharbor.service;

import java.util.List;

import com.virtusa.lawharbor.model.AdminModel;


public interface AdminServiceInterface {
	public AdminModel saveAdmin(AdminModel user);

	public List<AdminModel> getAllAdmin();

	public AdminModel getAdminById(int id);

	public void updateAdmin(AdminModel user, int id);

	public void deleteAdmin(int id);
	
	public AdminModel getByEmail(String email);
	
	public boolean existsAdminByEmail(String email);
	
	public void updateAdminActiveByEmail(String email, boolean active);
}
