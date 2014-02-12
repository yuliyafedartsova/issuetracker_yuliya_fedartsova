package org.training.issuetracker.controllers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;


public class ContextListener implements ServletContextListener {

    
    public ContextListener() {
        
    }

	
    public void contextInitialized(ServletContextEvent arg0) {
    	Configurations.PATH = arg0.getServletContext().getRealPath(Constants.DELIMITER);
    	Configurations.DB = 
    			arg0.getServletContext().getInitParameter(Constants.DATA_BASE);
    	Configurations.DB_DRIVER_NAME = 
    			arg0.getServletContext().getInitParameter(Constants.DRIVER);
    	Configurations.DB_NAME = 
    			arg0.getServletContext().getInitParameter(Constants.DATA_BASE_NAME);
    	Configurations.DB_PASSWORD = 
    			arg0.getServletContext().getInitParameter(Constants.PASSWORD);
    	Configurations.DB_USER = 
    			arg0.getServletContext().getInitParameter(Constants.USER);
    	arg0.getServletContext().setAttribute("path", Configurations.PATH);
        User guest = new User();
        guest.setRole(new Role(Constants.GUEST));
        arg0.getServletContext().setAttribute(Constants.USER, guest);
        String contextPath = arg0.getServletContext().getContextPath();
        arg0.getServletContext().setAttribute(Constants.PATH, contextPath);
    
    }

	
    public void contextDestroyed(ServletContextEvent arg0) {
       
    }
	
}
