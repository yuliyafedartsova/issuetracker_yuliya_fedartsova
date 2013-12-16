package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    	String projectId = request.getParameter("id");
    	if(projectId != null) {
    		int id = Integer.parseInt(projectId);
    		Project project = projectDAO.getProjectById(id);
    		request.setAttribute("project", project);
    		UserDAO userDAO = UserFactory.getClassFromFactory();
    		List<User> users = userDAO.getUsers();
    		request.setAttribute("users", users);
    		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProjectUpdateView");
      	   	rd.forward(request, response);
    	} else {
    		List<Project> projects = projectDAO.getProjects();
    	   	request.setAttribute("projects", projects);
    	   	RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProjectsView");
      	   	rd.forward(request, response);
    	}
    }

	

}
