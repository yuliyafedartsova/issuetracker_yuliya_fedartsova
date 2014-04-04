package org.training.issuetracker.controllers.spring;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.utils.HibernateSessionFactory;


@Controller
public class IssueReviewSpringController {

	@RequestMapping("/issue-review")
	public String reviewIssue(ModelMap model, @RequestParam("id") int id) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    String query = "from Issue where id=:id";
	    Query q = session.createQuery(query);
	    q.setParameter("id", id);
	    Issue issue = (Issue) q.uniqueResult();
	    session.getTransaction().commit();
	    model.addAttribute(Constants.ISSUE, issue);
	    return "/review_issue";
	
	
	}





}
