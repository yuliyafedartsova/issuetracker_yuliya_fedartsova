package org.training.issuetracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.MessageProvider;
import org.training.issuetracker.model.beans.Test;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.HibernateSessionFactory;

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
		List<User> users  = new ArrayList<User>();
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    users = session.createCriteria(User.class).list();
	    System.out.println();
	    for(User user : users) {
	    	System.out.println(user.getFirstName());
	    	System.out.println(user.getRole().getName());
	    }
	    System.out.println();
		model.addAttribute("title", messageProvider.getTitle());
		model.addAttribute("message", messageProvider.getHelloMessage());
		return "hello";
	}
	
	
	
	
	
}
