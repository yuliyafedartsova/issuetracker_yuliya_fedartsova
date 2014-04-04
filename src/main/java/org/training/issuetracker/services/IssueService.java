package org.training.issuetracker.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class IssueService {

	public void save(Issue issue) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.save(issue);
	    session.getTransaction().commit();
	}
	
	public Issue getById(int id) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Issue)session.get(Issue.class, id);
	}
	
	public void update(Issue issue) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.update(issue);
	    session.getTransaction().commit();
	}
	
}
