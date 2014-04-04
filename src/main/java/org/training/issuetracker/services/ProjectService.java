package org.training.issuetracker.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.HibernateSessionFactory;

public class ProjectService {

	public List<Project> getProjects() {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    List<Project> projects = session.createCriteria(Project.class).list();
	    session.close();
	    return projects;
	}
	
	public Project getById(int id) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Project project = (Project)session.get(Project.class, id);
	    return project;
	}
	
	public void update(Project project) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.update(project);
		session.getTransaction().commit();
    }
	
	public void save(Project project) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(project);
		session.getTransaction().commit();
    }
	
	
	

}
