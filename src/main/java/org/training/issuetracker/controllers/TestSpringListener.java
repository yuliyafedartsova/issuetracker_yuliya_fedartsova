package org.training.issuetracker.controllers;
import javax.servlet.ServletContextEvent;
import org.springframework.web.context.ContextLoaderListener;
import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;


public class TestSpringListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Configurations.PATH = event.getServletContext().getRealPath(Constants.DELIMITER);
		Configurations.DB = event.getServletContext().getInitParameter(Constants.DATA_BASE);
		Configurations.DB_NAME = event.getServletContext().getInitParameter(Constants.DATA_BASE_NAME);
		Configurations.DB_DRIVER_NAME = 
    			event.getServletContext().getInitParameter(Constants.DRIVER);
		Configurations.DB_PASSWORD = 
    			event.getServletContext().getInitParameter(Constants.PASSWORD);
    	Configurations.DB_USER = 
    			event.getServletContext().getInitParameter(Constants.USER);
		super.contextInitialized(event);
	
	}
	
}
