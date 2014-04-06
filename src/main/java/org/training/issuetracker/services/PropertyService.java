package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class PropertyService extends PersistentService {
	
	
	public Property getById(Property property, int id) {
		Property prop;
		Session session = sessionFactory.openSession();
		prop = (Property)session.get(property.getClass(), id);
		return prop;
	}
	
	
	
	


}
