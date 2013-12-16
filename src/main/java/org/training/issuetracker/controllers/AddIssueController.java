package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Properties;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssuePropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;

/**
 * Servlet implementation class AddIssueController
 */
public class AddIssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddIssueController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IssuePropertyDAO propertyDAO = IssuePropertyFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	List<PropertyParameter> statuses = 
    			propertyDAO.getPropertyParameters(Properties.STATUSES.toString().toLowerCase());
    	List<PropertyParameter> priorities = 
    			propertyDAO.getPropertyParameters(Properties.PRIORITIES.toString().toLowerCase());
    	List<PropertyParameter> types = 
    			propertyDAO.getPropertyParameters(Properties.TYPES.toString().toLowerCase());
    	List<User> users = userDAO.getUsers();
    	List<PropertyParameter> availableStatuses = new ArrayList<PropertyParameter>();
    	for(PropertyParameter par : statuses) {
    		if(par.getName().equals( "New") || par.getName().equals("Assigned") ) {
    			availableStatuses.add(par);
    		}
    	}
    	statuses = availableStatuses;
    	request.setAttribute("statuses", statuses);
    	request.setAttribute("types", types);
    	request.setAttribute("priorities", priorities);
    	request.setAttribute("users", users);
        
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddIssueView");
		rd.forward(request, response);
    
    
    }
	
}
