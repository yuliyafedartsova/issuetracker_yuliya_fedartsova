package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
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
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	List<PropertyParameter> statuses = propertyDAO.getStatuses();
    	List<PropertyParameter> priorities = propertyDAO.getPriorities();
    	List<PropertyParameter> types = propertyDAO.getTypes();
    	List<User> users = userDAO.getUsers();
    	List<PropertyParameter> availableStatuses = new ArrayList<PropertyParameter>();
    	List<Project> projects = projectDAO.getProjects();
   		
    	for(PropertyParameter par : statuses) {
    		if(par.getName().equals(Constants.NEW) || par.getName().equals(Constants.ASSIGNED) ) {
    			availableStatuses.add(par);
    		}
    	}
    	statuses = availableStatuses;
    	request.setAttribute(Constants.STATUSES, statuses);
    	request.setAttribute(Constants.TYPES, types);
    	request.setAttribute(Constants.PRIORITIES, priorities);
    	request.setAttribute(Constants.USERS, users);
    	request.setAttribute(Constants.PROJECTS, projects);
    	jumpPage("/AddIssueView", request, response);
    }
}