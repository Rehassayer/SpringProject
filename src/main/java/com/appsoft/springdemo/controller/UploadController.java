package com.appsoft.springdemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

	@GetMapping("/upload")
	public String getUpload(HttpSession session) {
		
		if (session.getAttribute("activeuser") ==null) {
			
			return "LoginForm";
		}
		return "UploadForm";
		
	}
	
	@PostMapping("/upload")
	public String postUpload(@RequestParam ("image") MultipartFile image,Model model) throws IOException {
		
		if(!image.isEmpty()) {
			
			long sizeinKB = image.getSize()/1024;
			if(sizeinKB > 200) {
				model.addAttribute("message","sorry! Max file size is 200KB ");
				return "UploadForm";
			}
			Files.copy(image.getInputStream(),Path.of("src/main/resources/static/images/"+image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			model.addAttribute("message","upload sucess");
			return "UploadForm";
		}
		
		model.addAttribute("message","upload failed  !!");
		return "UploadForm";
	}
}
