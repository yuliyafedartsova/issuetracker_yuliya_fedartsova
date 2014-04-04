package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class TypeService {

	public List<Type> getTypes() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
		return session.createCriteria(Type.class).list();
	}
	
	public Type getById(int id) {
	    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Type)session.get(Type.class, id);
	}


}
