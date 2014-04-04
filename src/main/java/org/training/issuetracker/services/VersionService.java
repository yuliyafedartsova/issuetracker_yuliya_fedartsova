package org.training.issuetracker.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.HibernateSessionFactory;



public class VersionService {

	public Version getById(int id) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		return (Version)session.get(Version.class, id);
	}
	
	public void save(Version version) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(version);
		session.getTransaction().commit();
	}

}
