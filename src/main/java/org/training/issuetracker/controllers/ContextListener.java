package org.training.issuetracker.controllers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
public class ContextListener implements ServletContextListener {

    
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextInitialized(ServletContextEvent arg0) {
        Constants.PATH = arg0.getServletContext().getRealPath(Constants.DELIMITER);
        User guest = new User();
        guest.setRole(new Role(Constants.GUEST));
        arg0.getServletContext().setAttribute(Constants.USER, guest);
    }

	
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
