package com.luminar.placementportal.service;

import java.util.List;

import com.luminar.placementportal.model.LoginModel;

public interface LoginService {
	
	List<LoginModel>getAllUsers();
	
	void saveUser(LoginModel login);
	
	LoginModel getUserById(long id);
	
	void deleteUserById(long id);
	
	String login(String userName, String password);

}
