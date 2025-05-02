package com.luminar.placementportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.placementportal.model.LoginModel;
import com.luminar.placementportal.repository.LoginRepository;

@Service

public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public List<LoginModel> getAllUsers(){
		return loginRepository.findAll();
	}
	
	@Override
    public String login(String userName, String password) {
        LoginModel login = loginRepository.getByUserName(userName);
        if (login == null || !login.getUserPassword().equals(password)) {
            return "redirect:/login?error=true"; // login failed
        }

        String role = login.getUserRole();
        if ("admin".equals(role)) {
            return "redirect:/admin";
        } else if ("backofficer".equals(role)) {
            return "redirect:/student";
        } else {
            return "redirect:/accessDenied";
        }
    }
	
	@Override
	public void saveUser(LoginModel login) {
		this.loginRepository.save(login);
	}
	
	@Override
	public LoginModel getUserById(long id) {
		Optional<LoginModel> optional = loginRepository.findById(id);
		LoginModel login = null;
		
		if (optional.isPresent()) {
			login = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		
		return login;
	}
	
	@Override
	public void deleteUserById(long id) {
		this.loginRepository.deleteById(id);
	}
	
	
	
}
