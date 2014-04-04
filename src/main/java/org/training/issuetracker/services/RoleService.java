package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class RoleService {

	public List<Role> getRoles() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
		return session.createCriteria(Role.class).list();
	}


}
