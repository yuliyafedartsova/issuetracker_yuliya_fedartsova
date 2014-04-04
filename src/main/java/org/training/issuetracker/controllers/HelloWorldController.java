package org.training.issuetracker.controllers;

import java.sql.Date;
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
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.MessageProvider;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.Test;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
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
	
	
	
	
	
	
	
}
