package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class SubmitIssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public SubmitIssueController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	List<PropertyParameter> statuses = null;
    	List<PropertyParameter> priorities = null;
    	List<PropertyParameter> types = null;
    	List<User> users = null;
    	List<Project> projects = null;
    	try {
    	statuses = propertyDAO.getStatuses();
    	priorities = propertyDAO.getPriorities();
    	types = propertyDAO.getTypes();
    	users = userDAO.getUsers();
    	projects = projectDAO.getProjects();
    	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	statuses = getAvailableStatuses(statuses);
    	request.setAttribute(Constants.STATUSES, statuses);
    	request.setAttribute(Constants.TYPES, types);
    	request.setAttribute(Constants.PRIORITIES, priorities);
    	request.setAttribute(Constants.USERS, users);
    	request.setAttribute(Constants.PROJECTS, projects);
    	jumpPage(Constants.JUMP_ADD_ISSUE, request, response);
    }
    
    private List<PropertyParameter> getAvailableStatuses(List<PropertyParameter> statuses) {
    	List<PropertyParameter> availableStatuses = new ArrayList<PropertyParameter>();
    	for(PropertyParameter par : statuses) {
    		if(par.getName().equals(Constants.NEW) || par.getName().equals(Constants.ASSIGNED) ) {
    			availableStatuses.add(par);
    		}
    	}
    	return availableStatuses;
    }
}