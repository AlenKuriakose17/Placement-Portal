package com.luminar.placementportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luminar.placementportal.model.LoginModel;
import com.luminar.placementportal.service.LoginService;
import com.luminar.placementportal.service.PlacementService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService ;
	
	@Autowired
	private PlacementService placementService;
	
	//Login
	
	@GetMapping("/")
	public String viewHomepage(Model model) {
		
		LoginModel login = new LoginModel();
		model.addAttribute("login", login);
		return "index";
		
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("login")LoginModel loginmodel, HttpSession session) {
		session.setAttribute("loginName", loginmodel.getUserName());
		String userName = loginmodel.getUserName();
		String password = loginmodel.getUserPassword();
		return loginService.login(userName, password);
	}
	
	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("listUsers", loginService.getAllUsers());
		return "admin";
	}
	
	@GetMapping("/student")
	public String studentPage(Model model) {
		model.addAttribute("listPlacements", placementService.getAllPlacements());
		return "student";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
	@GetMapping("/showNewSignupForm")
	public String showSignupForm(Model model) {
	    model.addAttribute("signup", new LoginModel()); // or whatever model class you're using
	    return "sign_up"; // Thymeleaf will render sign_up.html
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("signup") LoginModel login) {
// save User to database
		loginService.saveUser(login);
		return "redirect:/";
	}
	
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
	    model.addAttribute("addupdate", new LoginModel()); // or whatever model class you're using
	    return "add_user"; // Thymeleaf will render add_user.html
	}
	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		LoginModel login = loginService.getUserById(id);
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("addupdate", login);
		return "update_user";
	}
	
	@PostMapping("/saveUserAdmin")
	public String saveUserAdmin(@ModelAttribute("addupdate") LoginModel login) {
// save User to database
		loginService.saveUser(login);
		return "redirect:/admin";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {
		// call delete user method
		this.loginService.deleteUserById(id);
		return "redirect:/admin";
	}
	
  /*	@GetMapping("/viewUserList")
	public String viewUserList(Model model) {
		model.addAttribute("listUsers", loginService.getAllUsers());
		return "admin";
	} */
	
	
}
