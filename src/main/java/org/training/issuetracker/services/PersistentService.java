package org.training.issuetracker.services;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Persistent;


public class PersistentService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void update(Persistent persistent) {
		System.out.println("Before get current session");
		Session session = sessionFactory.getCurrentSession();
		System.out.println("After get current session");
		Transaction dbTransaction = session.getTransaction();
		System.out.println("After get transaction");
		dbTransaction.begin();
		System.out.println("After begin transaction");
	    session.update(persistent);
	    System.out.println("Before begin transaction");
	    dbTransaction.commit();
	}
	
	
	
	
	
	
	
	public void save(Persistent persistent) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.save(persistent);
	    session.getTransaction().commit();
	}
	
}
