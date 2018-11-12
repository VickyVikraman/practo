package com.practo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practo.model.ZohoBooksConfig;
import com.practo.service.ZohoBooksConfigService;

@Controller
public class BooksController {
	
 // Wiring services to controller
	@Autowired
	private ZohoBooksConfigService zohoBooksConfigService;
	
 // updating zoho books table with new zoho books
	@RequestMapping(value="/updatezohobooks", method = RequestMethod.POST)
	public String updateZohoBooks(@ModelAttribute ZohoBooksConfig zohobooks) {
		zohoBooksConfigService.addZohoBooks(zohobooks);
		return "redirect:/";
	}
}
