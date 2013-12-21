package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class IssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public IssueController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter(Constants.ID));
  	   	Issue issue = null;
  	   	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
  	   	List<User> users = null;
 		List<Project> projects = null;
 		List<PropertyParameter> statuses = null;
 		List<PropertyParameter> priorities = null;
 		List<PropertyParameter> types = null;
 		List<PropertyParameter> resolutions = null;
  	   	try {
  	   	issue = issuesDao.getIssueById(id);
  	   	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
  	   	request.setAttribute(Constants.ISSUE, issue);
  	    HttpSession session = request.getSession();
  	   	User user = (User)session.getAttribute(Constants.USER);
  	   	if(user == null) {
    		jumpPage(Constants.JUMP_ISSUE_REVIEW, request, response);
    	} else {
    		
    		
    		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
      	   	UserDAO userDAO = UserFactory.getClassFromFactory();
      	    PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
       		try {
      	    users = userDAO.getUsers();
       		projects = projectDAO.getProjects();
       		statuses = propertyDAO.getStatuses();
        	priorities = propertyDAO.getPriorities();
        	types = propertyDAO.getTypes();
        	resolutions = propertyDAO.getResolutions();
        	statuses = getAvailableStatuses(statuses, issue);
       		}catch (DaoException e) {
    			jumpPage(Constants.ERROR, request, response);
    			return;
    		}
        	request.setAttribute(Constants.PROJECTS, projects);
        	request.setAttribute(Constants.USERS, users);
        	request.setAttribute(Constants.PRIORITIES, priorities);
        	request.setAttribute(Constants.STATUSES, statuses);
        	request.setAttribute(Constants.TYPES, types);
        	request.setAttribute(Constants.RESOLUTIONS, resolutions);
       		jumpPage(Constants.JUMP_ISSUE_UPDATE, request, response);
    	}
    }
    
    private List<PropertyParameter> getAvailableStatuses(List<PropertyParameter> statuses, Issue issue) {
    	List<PropertyParameter> availableStatuses = new ArrayList<PropertyParameter>();
    	switch(issue.getStatus()) {
    		case Constants.NEW:
    			for(PropertyParameter par : statuses) {
        			if(par.getName().equals(Constants.NEW) || par.getName().equals(Constants.ASSIGNED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    		case Constants.ASSIGNED:
    			for(PropertyParameter par : statuses) {
        			if(par.getName().equals(Constants.IN_PROGRESS) || par.getName().equals(Constants.ASSIGNED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;	
    		case Constants.CLOSED:
    			for(PropertyParameter par : statuses) {
        			if(par.getName().equals(Constants.CLOSED) || par.getName().equals(Constants.REOPENED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    		case Constants.IN_PROGRESS:
    			for(PropertyParameter par : statuses) {
        			if(par.getName().equals(Constants.CLOSED) || par.getName().equals(Constants.IN_PROGRESS) ||
        					par.getName().equals(Constants.RESOLVED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    	}
    	
    	return availableStatuses;
    }
}
