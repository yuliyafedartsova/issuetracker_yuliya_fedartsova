package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
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

/**
 * Servlet implementation class IssueController
 */
public class IssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public IssueController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	int id = Integer.parseInt(request.getParameter(Constants.ID));
  	   	Issue issue = null;
  	   	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
  	   	issue = issuesDao.getIssueById(id);
  	   	request.setAttribute(Constants.ISSUE, issue);
    	if("review".equals(action)) {
    		jumpPage("/IssueReviewView", request, response);
    	} else {
    		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
      	   	UserDAO userDAO = UserFactory.getClassFromFactory();
      	    PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
       		List<User> users = userDAO.getUsers();
       		request.setAttribute(Constants.USERS, users);
       		List<Project> projects = projectDAO.getProjects();
       		request.setAttribute(Constants.PROJECTS, projects);
       		List<PropertyParameter> statuses = propertyDAO.getStatuses();
        	List<PropertyParameter> priorities = propertyDAO.getPriorities();
        	List<PropertyParameter> types = propertyDAO.getTypes();
        	List<PropertyParameter> resolutions = propertyDAO.getResolutions();
        	request.setAttribute(Constants.PRIORITIES, priorities);
        	request.setAttribute(Constants.STATUSES, statuses);
        	request.setAttribute(Constants.TYPES, types);
        	request.setAttribute(Constants.RESOLUTIONS, resolutions);
       		jumpPage("/IssueUpdateView", request, response);
    	}
    	
    }
    
 }
