package com.luminar.placementportal.service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository userRepository;

	public List<Login> getAllUsers() {
		return userRepository.findAll();
	}
//edited-----START------------------------------------------------------------------	

	/*
	 * public String login(String username) {
		 * User user = userRepository.findByUserName(username); 
		 * //User user=null; 
		 * if(user == null) { 
		 * 		System.out.println("User not found for username:"+username);       //my code
		 *		return "redirect:error"; 
		 * } 
		 * System.out.println("Username entered: "+ username); 
		 * String role=user.getUserRole();
		 *  System.out.println(role); 
		 *  if("admin".equals(role)) { 
		 *  	return "redirect:/admin"; // redirect to home page 
		 *  }else if ("backofficer".equals(role)) { 
		 *  	return "redirect:/backOfficer"; 
		 *  }elseif ("user".equals(role)) { 
		 * 		return "redirect:/user"; 
		 *  }else
		 * 		System.out.println("You have no access"); 
		 * 		return role;
	 * 
	 * }
	 */
    public String login(String userName, String password) {
        Login user = userRepository.getByUserName(userName);
        if (user == null || !user.getUserPassword().equals(password)) {
            return "redirect:/login?error=true"; // login failed
        }

        String role = user.getUserRole();
        if ("admin".equals(role)) {
            return "redirect:/admin";
        } else if ("backofficer".equals(role)) {
            return "redirect:/backOfficer";
        } else if ("user".equals(role)) {
            return "redirect:/user";
        } else {
            return "redirect:/accessDenied";
        }
    }
	


//edit------------------------------ END-----------------------------------------
	public void saveUser(Login user) {
		this.userRepository.save(user);
	}

	public Login getUserById(long id) {
		Optional<Login> optional = userRepository.findById(id);
		Login user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return user;
	}

	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

}	