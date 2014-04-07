package org.training.issuetracker.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;

public class SessionFactoryManager {

	private SessionFactory sessionFactory;
    private ServiceRegistry serviceRegistry;

    public SessionFactoryManager() {
    	try {
    		Configuration configuration = new Configuration()
    		.setProperty("hibernate.connection.driver_class", Configurations.DB_DRIVER_NAME)
    		.setProperty("hibernate.connection.url", Constants.DRIVER_TYPE + Configurations.DB + ":" + "D:/db/" + Configurations.DB_NAME)
    		.setProperty("hibernate.connection.username", Configurations.DB_USER)
    		.setProperty("hibernate.connection.password", Configurations.DB_PASSWORD)
    		.setProperty("hibernate.connection.pool_size", "10")
    		.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
    		.setProperty("hibernate.show_sql", "true")
    		.setProperty("hibernate.hbm2ddl.auto", "update")
    		.setProperty("hibernate.connection.autocommit", "false")
    		.setProperty("hibernate.current_session_context_class", "thread")
    		.addAnnotatedClass(org.training.issuetracker.model.beans.Persistent.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.Property.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.Project.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.User.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Resolution.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Role.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Status.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Type.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Version.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.Issue.class)
    		.addAnnotatedClass(org.training.issuetracker.model.beans.properties.Priority.class);

    		serviceRegistry = new StandardServiceRegistryBuilder()
    		.applySettings(configuration.getProperties()).build();

    		System.out.println(serviceRegistry);


    		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    		} catch (Exception e) {
    		e.printStackTrace();
    }
    
}

public SessionFactory getSessionFactory() {
		return sessionFactory;
}
}
