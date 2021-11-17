package com.simplilearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.dao.ProductDAO;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO productDao;	
	
	@RequestMapping(value="/list-products", method=RequestMethod.GET)
	public ModelAndView listProducts() {
		ModelAndView modelAndView = new ModelAndView("list-products");
		modelAndView.addObject("list", productDao.getProducts());
		return modelAndView;
	}
}
