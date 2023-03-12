package com.virtusa.lawharbor.service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.AdminDao;
import com.virtusa.lawharbor.model.AdminModel;
@Service
public class AdminServiceImpl implements AdminServiceInterface {

	private static final Logger log = Logger.getLogger(AdminServiceImpl.class.getName());
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public AdminModel saveAdmin(AdminModel user) {
		
		try {
			if (user == null) {
				log.log(Level.INFO, "User Deleted successfully .");
				return null;
			} else if (this.adminDao.findByEmail(user.getEmail()) == user) {
				log.log(Level.WARNING, "Admin alrady exists :{0} ", user);
				return null;
			} else {
				this.adminDao.save(user);
				log.log(Level.INFO, "Admin Saved successfully .");
			}

		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in create Admin : ", e);
		}
		return user;
	}

	@Override
	public List<AdminModel> getAllAdmin() {
		try {
			return this.adminDao.findAll();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in get All Admin Service : ", e);
		}
		return Collections.emptyList();
	}

	@Override
	public AdminModel getAdminById(int id) {
		AdminModel admin;
		try {
			if (id == 0) {
				log.log(Level.WARNING, "User id Not given or must not be zero .");
			}
			if (this.adminDao.existsById(id)) {
				admin = this.adminDao.findById(id).get();
				log.log(Level.INFO, "User data :{0} .", admin.getName());
				return admin;
			} else {

				log.log(Level.WARNING, "User does not exists with id :{0} ", id);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in get user : ", e);
		}

		return null;
	}

	@Override
	public void updateAdmin(AdminModel user, int id) {
		try {
			if (user == null) {
				log.warning("No data Found in Update service.");
				return;
			}
			if (this.adminDao.existsById(id)) {
				this.adminDao.save(user);
				log.log(Level.INFO, "Admin data updated. :{0}", user);
			} else {
				log.log(Level.WARNING, "Admin does not exists with id :{0} ", id);
			}

		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in Update user service : ", e);
		}

	}

	@Override
	public void deleteAdmin(int id) {
		try {
			if (id == 0) {
				log.warning("No data Found in Delete service.");
				return;
			}
			if (this.adminDao.existsById(id)) {
				this.adminDao.deleteById(id);
				log.log(Level.INFO, "Admin Deleted successfully .");
			} else {
				log.log(Level.WARNING, "Admin does not exists with id :{0} ", id);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in Delete Admin service : ", e);
		}

	}

	@Override
	public AdminModel getByEmail(String email) {
		AdminModel admin;
		try {
			if (email.isBlank()) {
				log.log(Level.WARNING, "User email Not given or must not be zero .");
			}
			if (this.adminDao.existsByEmail(email)) {
				admin = this.adminDao.findByEmail(email);
				log.log(Level.INFO, "admin data :{0} .", admin.getName());
				return admin;
			} else {

				log.log(Level.WARNING, "Admin does not exists with Email :{0} ", email);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in get Admin By id : ", e);
		}

		return null;
	}

	@Override
	public void updateAdminActiveByEmail(String email, boolean active) {
		try {
			if (email == null) {
				log.warning("No data Found in Update service.");
				return;
			}
			if (this.adminDao.existsByEmail(email)) {
				this.adminDao.updateActiveByEmail(active, email);
				log.log(Level.INFO, "Admin data updated.");
			} else {
				log.log(Level.WARNING, "Admin does not exists with email :{0} ", email);
			}

		} catch (Exception e) {
			log.log(Level.WARNING, "Error occurred in Update user service : ", e);
		}
		
	}

	@Override
	public boolean existsAdminByEmail(String email) {
		
		return adminDao.existsByEmail(email);
	}

}
