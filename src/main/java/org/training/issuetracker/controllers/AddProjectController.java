package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.UserDAO;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ProjectInstanceFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class AddProjectController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddProjectController() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	try {
        Project project = ProjectInstanceFactory.getProject(request);
        projectDAO.addProject(project);
        } catch (DaoException e) {
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}
    
    }
	
}
//