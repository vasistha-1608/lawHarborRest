package com.virtusa.lawharbor.service;

import java.util.List;

import com.virtusa.lawharbor.model.User;

public interface UserServiceInterface {
	public User saveUser(User user);

	public List<User> getAllUser();

	public User getUserById(int id);

	public void updateUser(User user, int id);

	public void deleteUser(int id);
	
	public User getByEmail(String email);
	
	public boolean checkUserByEmail(String email);
	
	public void updateUserActiveByEmail(String email, boolean active);
}
