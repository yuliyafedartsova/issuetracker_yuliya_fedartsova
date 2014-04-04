package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class StatusService {
	public List<Status> getStatuses() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
		return session.createCriteria(Status.class).list();
	}
	
	public Status getByName(String name) {
		Status status;
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
		session.beginTransaction();
	    String query = "from Status where name=:name";
	    Query q = session.createQuery(query);
	    q.setParameter("name", name);
	    status = (Status) q.uniqueResult();
	    session.getTransaction().commit();
	    return status;
	}

	public Status getById(int id) {
	    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Status)session.get(Status.class, id);
	}

	public void update(Property status) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.update(status);
	    session.getTransaction().commit();
	
	}






}
