package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.utils.DisplayManager;


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
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}catch (Exception e) {
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}
    	statuses = DisplayManager.getAvailableStatusesForSubmitIssue(statuses);
    	request.setAttribute(Constants.STATUSES, statuses);
    	request.setAttribute(Constants.TYPES, types);
    	request.setAttribute(Constants.PRIORITIES, priorities);
    	request.setAttribute(Constants.USERS, users);
    	request.setAttribute(Constants.PROJECTS, projects);
    	jumpPage(Pages.ADD_ISSUE_PAGE, request, response);
    }
    
    
}