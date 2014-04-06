package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class PriorityService extends PropertyService {
	
	public List<Priority> getPriorities() {
		Session session = sessionFactory.openSession();
		return (List<Priority>)session.createCriteria(Priority.class).list();
	}

	public Priority getById(int id) {
	    Session session = sessionFactory.openSession();
		return (Priority)session.get(Priority.class, id);
	}


}
