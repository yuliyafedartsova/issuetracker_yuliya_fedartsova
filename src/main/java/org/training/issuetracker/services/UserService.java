package org.training.issuetracker.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;


public class UserService extends PersistentService {
	
	public List<User> getUsers() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(User.class).list();
	}
	
	public User getById(int id) {
		Session session = sessionFactory.openSession();
		return (User)session.get(User.class, id);
	}
	
}
