package com.xyzl.www.xyzl.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping("/getUser")
	public void getUser(){

		logger.info("getUser");
		
	}
	
}
