package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class PriorityService {
	
	public List<Priority> getPriorities() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
		return session.createCriteria(Priority.class).list();
		
	}

	public Priority getById(int id) {
	    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Priority)session.get(Priority.class, id);
	}


}
