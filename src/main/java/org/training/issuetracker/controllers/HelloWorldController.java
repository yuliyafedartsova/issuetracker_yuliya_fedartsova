package org.training.issuetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.model.beans.MessageProvider;
import org.training.issuetracker.model.beans.properties.Version;

@Controller

public class HelloWorldController {
	@Autowired
	private MessageProvider messageProvider;
	
	
	@RequestMapping("/hello")
	public String test2(ModelMap model, @ModelAttribute("version") Version version ) {
		model.addAttribute("title", messageProvider.getTitle());
		model.addAttribute("message", version.getName());
		return "hello";
	}
	
	@RequestMapping("/hello/{var}")
	public String test(ModelMap model, @PathVariable String var) {
		model.addAttribute("title", messageProvider.getTitle());
		model.addAttribute("message", var);
		return "hello";
	}
	
	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		
	
		model.addAttribute("title", messageProvider.getTitle());
		model.addAttribute("message", messageProvider.getHelloMessage());
		return "hello";
	}
	
	
	
	
	
}
