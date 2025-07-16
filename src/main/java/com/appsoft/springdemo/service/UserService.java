package com.appsoft.springdemo.service;

import com.appsoft.springdemo.model.User;

public interface UserService {
 
	void userSignup(User u);
	User userLogin( String un,String pw);
}
