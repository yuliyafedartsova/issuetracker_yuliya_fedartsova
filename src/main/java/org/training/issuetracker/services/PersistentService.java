package org.training.issuetracker.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Persistent;


public class PersistentService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void update(Persistent persistent) {
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.update(persistent);
	    session.getTransaction().commit();
	}
	
	public void save(Persistent persistent) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.save(persistent);
	    session.getTransaction().commit();
	}
	
	public void test(){
		Session session = sessionFactory.openSession();
	}
	
	

}
