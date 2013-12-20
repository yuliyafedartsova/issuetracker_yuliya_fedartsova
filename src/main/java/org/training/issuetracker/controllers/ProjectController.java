package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class ProjectController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ProjectController() {
        super();
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("IN PROJECT CONTROLLER");
    	
    	
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	String action = request.getParameter(Constants.ACTION);
    	List<Project> projects = null;
    	List<User> users = null;
    	Project project = null;
    	if(Constants.REVIEW.equals(action)) {
    		try {
    		projects = projectDAO.getProjects();
    		}catch (DaoException e) {
    			jumpPage(Constants.ERROR, request, response);
    			return;
    		}
    		cutDescriptionsOff(projects);
    		request.setAttribute(Constants.PROJECTS, projects);
    		jumpPage(Constants.JUMP_PROJECTS, request, response);
    	} else {
    		UserDAO userDAO = UserFactory.getClassFromFactory();
    		try {
    		users = userDAO.getUsers();
    		}catch (DaoException e) {
    			jumpPage(Constants.ERROR, request, response);
    			return;
    		}
    		request.setAttribute(Constants.USERS, users);
    	    if(Constants.ADD.equals(action)) {
    	    	jumpPage(Constants.JUMP_ADD_PROJECT, request, response);
    	    } else {
    	    	String projectId = request.getParameter(Constants.ID);
        		int id = Integer.parseInt(projectId);
        		try {
        		project = projectDAO.getProjectById(id);
        		}catch (DaoException e) {
        			jumpPage(Constants.ERROR, request, response);
        			return;
        		}
        		request.setAttribute(Constants.PROJECT, project);
        		jumpPage(Constants.JUMP_UPDATE_PROJECT, request, response);
    	    }
    	}
    }
    
    protected void cutDescriptionsOff(List<Project> projects) {
    	for(Project project : projects) {
    		if(project.getDescription().length() > Constants.DESCRIPTION_LENTH_LIMIT){
    			String description = 
    					project.getDescription().substring(Constants.NULL, 
    							Constants.DESCRIPTION_LENTH_LIMIT);
    			description += Constants.THREE_DOTS;
    			project.setDescription(description);
    		}
    	}
    }
}
