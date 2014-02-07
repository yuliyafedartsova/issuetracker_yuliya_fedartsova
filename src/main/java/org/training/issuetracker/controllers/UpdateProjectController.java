package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;

import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class UpdateProjectController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateProjectController() {
        super();
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	int id = Integer.parseInt(request.getParameter(Constants.ID));
    	String name = request.getParameter(Constants.NAME);
        String description = request.getParameter(Constants.DESCRIPTION);
        int managerId = Integer.parseInt(request.getParameter(Constants.MANAGER));   
        UserDAO userDAO = UserFactory.getClassFromFactory();
        String version = request.getParameter(Constants.VERSION);
        try {
        	User manager = userDAO.getUserById(managerId);
        	if(!version.isEmpty()) {
            	projectDAO.addVersion(version, id);
            }
        	List<Version> versions = projectDAO.getVersionsOfProject(id);
        	Project project = new Project(id, name, manager, versions, description);
        	projectDAO.updateProject(project);
        } catch (DaoException e) {
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}
        
        
   
    
    }

	
	

}
