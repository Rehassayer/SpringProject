package com.appsoft.springdemo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springdemo.model.User;
import com.appsoft.springdemo.repository.UserRepository;
import com.appsoft.springdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void userSignup(User u) {
		
		userRepo.save(u);
	}

	@Override
	public User userLogin(String un, String pw) {
		
		return userRepo.checkUser(un,pw);
	}

	
}
