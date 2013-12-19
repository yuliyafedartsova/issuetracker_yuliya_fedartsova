package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;

/**
 * Servlet implementation class ProjectController
 */
public class ProjectController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ProjectController() {
        super();
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	String action = request.getParameter("action");
    	UserDAO userDAO = UserFactory.getClassFromFactory();
		List<User> users = userDAO.getUsers();
    	
    	
    	switch(action) {
    	case "show":
    		List<Project> projects = projectDAO.getProjects();
    		cutDescriptionsOff(projects);
    		request.setAttribute(Constants.PROJECTS, projects);
    		jumpPage("/ProjectsView", request, response);
    	    break;
    	case "update":
    		String projectId = request.getParameter(Constants.ID);
    		int id = Integer.parseInt(projectId);
    		Project project = projectDAO.getProjectById(id);
    		request.setAttribute(Constants.PROJECT, project);
    		request.setAttribute(Constants.USERS, users);
    		jumpPage("/ProjectUpdateView", request, response);
    		break;
    	case "add":
    		request.setAttribute(Constants.USERS, users);
    		jumpPage("/AddProjectView", request, response);
    		break;
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
