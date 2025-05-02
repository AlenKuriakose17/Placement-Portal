package com.luminar.placementportal.controller;

@Controller
public class LoginController {

	@Autowired
	private LoginService userService;

	// display list of users
	@GetMapping("/")
	public String showLoginPage(Model model) {
		Login user=new Login();  //new User() creates the object of the Entity class
		model.addAttribute("login",user); // 'login' matches to 'th:object="${login}"'in index.html and also matches to @ModelAttribute("login") in @PostMapping below.
		return "index"; // (index) redirecting HTML file name
	}
	
//edit---------------------------------Start-----------testing--------------------------------

	@PostMapping("/login")
    public String login(@ModelAttribute("login") Login user, HttpSession session) {
		session.setAttribute("userName", user.getUserName());
        String userName = user.getUserName();
        String password = user.getUserPassword();
        //session.setAttribute("userName",userName);
        return userService.login(userName, password);
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin"; // admin.html
    }

    @GetMapping("/backOfficer")
    public String backOfficerPage() {
        return "backOfficer/backOfficer"; // backofficer.html
    }

    @GetMapping("/user")
    public String userPage() {
        return "user/userHeader"; // user.html
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied"; // accessDenied.html
    }
    
   

//edit---------------------------------END-----------testing--3------------------------------
	@GetMapping("/showNewUserForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Login user = new Login();
		model.addAttribute("user", user);
		return "new_user";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") Login user) {
		// save user to database
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get user from the service
		Login user = userService.getUserById(id);
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {

		// call delete user method
		this.userService.deleteUserById(id);
		return "redirect:/";
	}
}