package org.training.issuetracker.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.HibernateSessionFactory;



public class VersionService extends PersistentService {
	
	public Version getById(int id) {
		Session session = sessionFactory.openSession();
		return (Version)session.get(Version.class, id);
	}
	
}
