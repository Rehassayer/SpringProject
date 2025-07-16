package com.appsoft.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springdemo.utils.EmailUtils;

@Controller
public class ContactController {

	@Autowired
	private EmailUtils mailUtil;
	@GetMapping("/contact")
	public String getContact() {
		
		return "ContactForm";
	}
	
	@PostMapping("/contact")
	public String postContact(@RequestParam ("toEmail") String toEmail,@RequestParam("subject") String subject,@RequestParam("message") String message) {
		
		
		mailUtil.sendEmail(toEmail, subject, message);
		return "ContactForm";
	}
}
