package org.training.issuetracker.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;


public class TypeService extends PropertyService {

	public List<Type> getTypes() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(Type.class).list();
	}
	
	public Type getById(int id) {
	    Session session = sessionFactory.openSession();
		return (Type)session.get(Type.class, id);
	}


}
