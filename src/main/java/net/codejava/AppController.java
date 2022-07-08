package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		// retun no of users
		model.addAttribute("noOfUsers", listUsers.size());

		return "users";
	}

	@GetMapping("/search")
	public String query(Model model) {
		return "query";
	}

	@GetMapping("/query")
	// query on basis of one of multiple RequestParams such as name, age,
	// profession,
	// email, mobileNumber
	public String query(@RequestParam(required = false, name = "name") String name,
			@RequestParam(name = "age", required = false) Integer age,
			@RequestParam(name = "profession", required = false) String profession,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "mobileNumber", required = false) String mobileNumber, Model model) {
		// code for if name="", then name is null
		if (name == "") {
			name = null;
		} else {
			// add % to the end of name to make it like %name%
			name = "%" + name + "%";
		}
		if (profession == "") {
			profession = null;
		}
		if (email == "") {
			email = null;
		} else {
			email = "%" + email + "%";
		}
		if (mobileNumber == "") {
			mobileNumber = null;
		}
		if (age == null) {
			age = null;
		}
		List<User> listUsers = userRepo.customQuery(name, age, profession, email, mobileNumber);
		model.addAttribute("listUsers", listUsers);
		if (listUsers.isEmpty()) {
			model.addAttribute("message", "No results found");
		} else {
			// n results found
			model.addAttribute("message", listUsers.size() + " results found");
		}

		return "query";
	}

	// redirect to error page in case of invalid url
	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
