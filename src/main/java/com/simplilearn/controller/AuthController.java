package com.simplilearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.dao.UserDAO;
import com.simplilearn.model.Login;
import com.simplilearn.model.User;

@Controller
public class AuthController {

	@Autowired
	UserDAO userDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister() {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}

	@RequestMapping(value = "/register-process", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		userDao.registerUser(user);
		return new ModelAndView("welcome", "firstname", user.getFirstname());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;
	}

	@RequestMapping(value = "/login-process", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("login") Login login) {
		User user = userDao.loginUser(login);
		ModelAndView mav = null;
		if (null != user) {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Invalid credentials, Username or Password is wrong!!");
		}

		return mav;
	}
}
