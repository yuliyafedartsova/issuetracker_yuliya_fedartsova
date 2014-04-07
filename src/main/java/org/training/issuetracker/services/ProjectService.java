package org.training.issuetracker.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Version;


public class ProjectService extends PersistentService {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Project> getProjects() {
		Session session = sessionFactory.openSession();
	    List<Project> projects = session.createCriteria(Project.class).list();
	    return projects;
	}
	
	public Project getById(int id) {
		Session session = sessionFactory.openSession();
		Project project = (Project)session.get(Project.class, id);
	    return project;
	}
	
}
