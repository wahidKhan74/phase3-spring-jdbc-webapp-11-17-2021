package com.simplilearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.dao.ProductDAO;
import com.simplilearn.model.Product;

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
	
	@RequestMapping(value="/open-product",method=RequestMethod.GET)
	public ModelAndView openAddProductPage() {
		ModelAndView modelAndView = new ModelAndView("add-product");
		Product product = new Product();
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	@RequestMapping(value="/add-product",method=RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("product") Product product) {
		ModelAndView modelAndView = new ModelAndView("success-reponse");
		productDao.addProduct(product);
		modelAndView.addObject("action","created");
		return modelAndView;
	}
	
	@RequestMapping(value="/open-update",method=RequestMethod.GET)
	public ModelAndView openUpdateProductPage() {
		ModelAndView modelAndView = new ModelAndView("update-product");
		Product product = new Product();
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	@RequestMapping(value="/update-product",method=RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
		ModelAndView modelAndView = new ModelAndView("success-reponse");
		productDao.updateProduct(product);
		modelAndView.addObject("action","updated");
		return modelAndView;
	}
	
	@RequestMapping(value="/open-delete",method=RequestMethod.GET)
	public ModelAndView openDeletetProductPage() {
		ModelAndView modelAndView = new ModelAndView("delete-product");
		Product product = new Product();
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete-product",method=RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute("product") Product product) {
		ModelAndView modelAndView = new ModelAndView("success-reponse");
		modelAndView.addObject("action","deleted");
		productDao.deleteProduct(product);
		return modelAndView;
	}
}
