package org.training.issuetracker.controllers.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.utils.HibernateSessionFactory;


@Controller
public class MainSpringController {
	@RequestMapping("/")
	public String main(ModelMap model) {
		List<Issue> issues = new ArrayList<Issue>();
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    issues = session.createCriteria(Issue.class).list();
	    model.addAttribute(Constants.ISSUES, issues);
		return "main";
	}
}
