package org.training.issuetracker.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Version;


public class ProjectService extends PersistentService {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional(readOnly=true)
	public List<Project> getProjects() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Project> projects = session.createCriteria(Project.class).list();
		session.getTransaction().commit();
		return projects;
	}
	
	public Project getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Project project = (Project)session.get(Project.class, id);
		session.getTransaction().commit();
		return project;
	}
	
}
