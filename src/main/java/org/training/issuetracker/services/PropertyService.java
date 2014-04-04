package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class PropertyService {

	public Property getById(Property property, int id) {
		Property prop;
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		prop = (Property)session.get(property.getClass(), id);
		return prop;
	}
	
	public void update(Property property) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.update(property);
	    session.getTransaction().commit();
	
	}
	
	public void save(Property property) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.save(property);
	    session.getTransaction().commit();
	
	}
	
	


}
