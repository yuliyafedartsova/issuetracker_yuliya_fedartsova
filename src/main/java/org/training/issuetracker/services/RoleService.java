package org.training.issuetracker.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;


public class RoleService extends PropertyService {
	
	public List<Role> getRoles() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(Role.class).list();
	}
	
	public Role getById(int id) {
		Session session = sessionFactory.openSession();
		return (Role)session.get(Role.class, id);
	}


}
