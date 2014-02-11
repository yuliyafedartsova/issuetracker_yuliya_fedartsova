package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ValidationManagers.ProjectValidator;


public class ProjectFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ProjectFormController() {
        super();
      
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	String action = request.getParameter(Constants.ACTION);
    	List<User> users = null;
    	Project project = null;
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	ProjectValidator validator = new ProjectValidator();
    	try {
    		users = userDAO.getUsers();
    	    request.setAttribute(Constants.USERS, users);
    	if(Constants.ADD.equals(action)) {
    	    jumpPage(Pages.ADD_PROJECT_PAGE, request, response);
    	} else {
    	    String projectId = request.getParameter(Constants.ID);
    	    validator.validateIdParameters(projectId);
    	    int id = Integer.parseInt(projectId);
        	project = projectDAO.getProjectById(id);
        }
    	}catch (DaoException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
        	
    	}catch (ValidationException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;	
    	}
        request.setAttribute(Constants.PROJECT, project);
        jumpPage(Pages.UPDATE_PROJECT_PAGE, request, response);
    }
}
    


