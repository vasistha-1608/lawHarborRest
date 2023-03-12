package com.virtusa.lawharbor.service;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.UserDao;
import com.virtusa.lawharbor.model.User;
import java.util.Collections;

@Service
public class UserService implements UserServiceInterface {

	private static final Logger log = LogManager.getLogger(UserService.class.getName());
	private static final String USER_NOT_EXISTS_WITH_ID = "User does not exists with id :{0} ";

	@Autowired
	private UserDao userDao;

	@Override
	public User saveUser(User user) {
		try {
			if (user == null) {
				log.log(Level.INFO, "User Deleted successfully .");
				return null;
			} else if (this.userDao.findByEmail(user.getEmail()) == user) {
				log.log(Level.WARN, "User alrady exists :{0} ", user);
				return null;
			} else {
				this.userDao.save(user);
				log.log(Level.INFO, "User Saved successfully .");
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in create user : ", e);
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {
		try {
			return this.userDao.findAll();
		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in get All User Service : ", e);
		}
		return Collections.emptyList();
	}

	@Override
	public User getUserById(int id) {
		User user;
		try {
			if (id == 0) {
				log.log(Level.WARN, "User id Not given or must not be zero .");
			}
			if (this.userDao.existsById(id)) {
				user = this.userDao.findById(id).get();
				log.log(Level.INFO, "User data :{0} .", user);
				return user;
			} else {

				log.log(Level.WARN, USER_NOT_EXISTS_WITH_ID, id);
			}
		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in get user : ", e);
		}

		return null;
	}

	@Override
	public void updateUser(User user, int id) {
		try {
			if (user == null) {
				log.warn("No data Found in Update service.");
				return;
			}
			if (this.userDao.existsById(id)) {
				this.userDao.save(user);
				log.log(Level.INFO, "User data updated. :{0}", user);
			} else {
				log.log(Level.WARN,USER_NOT_EXISTS_WITH_ID, id);
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Update user service : ", e);
		}

	}

	@Override
	public void deleteUser(int id) {
		try {
			if (id == 0) {
				log.warn("No data Found in Delete service.");
				return;
			}
			if (this.userDao.existsById(id)) {
				this.userDao.deleteById(id);
				log.log(Level.INFO, "User Deleted successfully .");
			} else {
				log.log(Level.WARN, USER_NOT_EXISTS_WITH_ID, id);
			}
		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Delete user service : ", e);
		}
	}

	@Override
	public User getByEmail(String email) {
		User user;
		try {
			if (email.isBlank() || email.isEmpty()) {
				log.log(Level.WARN, "User email Not given or must not be zero .");
			}
			if (this.userDao.existsByEmail(email)) {
				user = this.userDao.findByEmail(email);
				log.log(Level.INFO, "User data :{0} .", user);
				return user;
			} else {

				log.log(Level.WARN, USER_NOT_EXISTS_WITH_ID, email);
			}
		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in get user : ", e);
		}

		return new User();
	}

	@Override
	public boolean checkUserByEmail(String email) {
		try {
			if (this.userDao.existsByEmail(email)) {
				return true;
			}
		} catch (Exception e) {
			log.warn("Errror in check User By Email ",e);
		}
		return false;
	}

	@Override
	public void updateUserActiveByEmail(String email, boolean active) {
		try {
			if (email == null) {
				log.warn("No data Found in Update service.");
				return;
			}
			if (this.userDao.existsByEmail(email)) {
				this.userDao.updateActiveByEmail(active, email);
				log.log(Level.INFO, "User status changed. ");
			} else {
				log.log(Level.WARN,USER_NOT_EXISTS_WITH_ID, email);
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Update user service : ", e);
		}
	}

}
