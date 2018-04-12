package com.testing.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.testing.demo.beans.User;

@Service
public class UserServiceImpl implements UserService {
	
	Logger loger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setName("Ram");
		user.setEmail("ram@gmail.com");
		user.setAddress("New Delhi");
		return user;
	}

	@Override
	public String AddUser(User user) {
		loger.info("user ID************" + user.getId());
		loger.info("user Name************" + user.getName());
		loger.info("user Email************" + user.getEmail());
		loger.info("user Address************" + user.getAddress());
		return "user Added";
	}

}
