package org.training.issuetracker.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class IssueService extends PersistentService {
	
	
	public Issue getById(int id) {
		Session session = sessionFactory.openSession();
		return (Issue)session.get(Issue.class, id);
	}
	
	public List<Issue> getIssues() {
		Session session = sessionFactory.openSession();
	    return session.createCriteria(Issue.class).list();
	}
	
	public void test() {
		Session session = sessionFactory.openSession();
	}
	
}
