package com.aws.loginapp.controller;


import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aws.loginapp.model.User;
import com.aws.loginapp.repo.UserRepositry;


@Controller
@RequestMapping("/userLoginApp")
public class UserInfoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
    
	@Autowired
	UserRepositry repo;
	
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userLogin(Model model) {
		LOGGER.info("From Logger Enters in to UserLogin Page");
		model.addAttribute("userForm", new User());
		return "user-login";

	}

	
	
	@RequestMapping(value = "/userregister", method = RequestMethod.POST)
	public ModelAndView loginDetails(@ModelAttribute("userForm") User userForm) {
		ModelAndView mav = new ModelAndView();
		LOGGER.info("reg request came " + userForm);
		User newForm = repo.save(userForm);
		LOGGER.info("response " + newForm);
		mav.setViewName("reg-sucess");
		mav.addObject("user", newForm);
		return mav;
	}

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ModelAndView checkUserDetails(User user) {
		LOGGER.info("enter into home service for login" + user.getEmail());
		ModelAndView mav = new ModelAndView();
		User isValidLoginForm = repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		mav.setViewName("reg-sucess");
		mav.addObject("user", isValidLoginForm);
		return mav;
	}

}
