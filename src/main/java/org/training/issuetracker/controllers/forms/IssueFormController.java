package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.DisplayManager;


public class IssueFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public IssueFormController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	String url = null;
    	StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	try {
    	List<Status> statuses = statusDAO.getAll();
    	List<Priority> priorities = priorityDAO.getAll();
    	List<Type> types = typesDAO.getAll();
    	List<User> users = userDAO.getUsers();
    	List<Project> projects = projectDAO.getProjects();
    	switch(action) {
		case Constants.ADD:
			url = Pages.ADD_ISSUE_PAGE;
			statuses = DisplayManager.getAvailableStatusesForSubmitIssue(statuses);
			break;
		case Constants.UPDATE:
			int id = Integer.parseInt(request.getParameter(Constants.ID));
			IssueDAO issuesDao = IssueFactory.getClassFromFactory();
			Issue issue = issuesDao.getIssueById(id);
			ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
			List<Resolution> resolutions = resolutionDAO.getAll();
			request.setAttribute(Constants.ISSUE, issue);
			request.setAttribute(Constants.RESOLUTIONS, resolutions);
			url = Pages.UPDATE_ISSUE_PAGE;
			statuses = DisplayManager.getAvailableStatuses(statuses, issue);
			break;
	    }
    	request.setAttribute(Constants.PROJECTS, projects);
    	request.setAttribute(Constants.USERS, users);
    	request.setAttribute(Constants.PRIORITIES, priorities);
    	request.setAttribute(Constants.STATUSES, statuses);
    	request.setAttribute(Constants.TYPES, types);
    	jumpPage(url, request, response);
    	}catch (DaoException e) {
			
    		
    		jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}catch (Exception e) {
			
			
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}
    	
    }
	
}
