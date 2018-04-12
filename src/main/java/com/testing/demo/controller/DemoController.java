package com.testing.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testing.demo.beans.User;
import com.testing.demo.service.UserService;

@RestController
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	Logger loger=LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping("/test")
	@ResponseBody
	String setUpTest() {
		loger.debug("debug point **************");
		loger.info("logger point info************");
		loger.error("error point******");
		return "Setup Successfully Done";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {
		User user1= userService.getUser();
		return user1;
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	@ResponseBody
	public String AddUser(@RequestBody User user ) {
		String msg= userService.AddUser(user);
		return msg;
	}

}
