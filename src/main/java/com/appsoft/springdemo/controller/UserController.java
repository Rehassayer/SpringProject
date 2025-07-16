package com.appsoft.springdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springdemo.model.User;
import com.appsoft.springdemo.repository.ProductRepository;
import com.appsoft.springdemo.service.UserService;
import com.appsoft.springdemo.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		
		model.addAttribute("prodList",prodRepo.findAll());
		return "CustomerHome";
	}

	@GetMapping({ "/login" })
	public String getLogin() {

		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String gRCode) throws IOException {

		if (VerifyRecaptcha.verify(gRCode)) {

			u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
			User usr = userService.userLogin(u.getUsername(), u.getPassword());
			if (usr != null) {

				log.info("----user login sucess-------");

				session.setAttribute("activeuser", usr);
				session.setMaxInactiveInterval(400);
				// model.addAttribute("uname",usr.getFname());
				
				if (usr.getRole().equals("Customer")) {
					
					return "CustomerHome";
				}
				
				return "Home";
			}

			else {
				log.info("----user login failed !!!-------");
				model.addAttribute("message", "user not found!!");
				return "LoginForm";

			}
		}
		

			log.info("----user login failed !!!-------");
			model.addAttribute("message", " are you robot!!");
			return "LoginForm";
		

	}

	@GetMapping("/signup")
	public String getSignUp() {

		return "SignUpForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User u) {

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.userSignup(u);
		return "LoginForm";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		log.info("----user logout -------");
		session.invalidate(); // kill session
		return "LoginForm";
	}

	@GetMapping("/profile")
	public String getProfile(HttpSession session) {

		if (session.getAttribute("activeuser") == null) {

			return "LoginForm";
		}

		return "Profile";
	}

	@GetMapping("/home")
	public String home() {

		return "Home";
	}
}
