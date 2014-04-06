package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class ResolutionService extends PropertyService {
	
	public List<Resolution> getResolutions() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(Resolution.class).list();
	}
	
	public Resolution getById(int id) {
		Session session = sessionFactory.openSession();
		return (Resolution)session.get(Resolution.class, id);
	}
	

}
