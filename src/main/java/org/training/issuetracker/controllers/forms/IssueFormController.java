package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
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



public class IssueFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public IssueFormController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute(Constants.USER);
    	if(user == null || user.getRole().getName() == Constants.GUEST) {
			jumpPage(Constants.MAIN, request, response);
			return;
		}
    	String action = request.getParameter(Constants.ACTION);
    	String url = null;
    	TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	try {
    	List<Status> statuses = null;
    	List<Priority> priorities = priorityDAO.getAll();
    	List<Type> types = typesDAO.getAll();
    	List<User> users = userDAO.getUsers();
    	List<Project> projects = projectDAO.getProjects();
    	switch(action) {
		case Constants.ADD:
			url = Pages.ADD_ISSUE_PAGE;
			statuses = getAvailableStatusesForSubmitIssue();
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
			statuses = getAvailableStatuses(issue);
			break;
	    }
    	request.setAttribute(Constants.USERS, users);
    	request.setAttribute(Constants.PRIORITIES, priorities);
    	request.setAttribute(Constants.STATUSES, statuses);
    	request.setAttribute(Constants.TYPES, types);
    	request.setAttribute(Constants.PROJECTS, projects);
    	jumpPage(url, request, response);
    	}catch (DaoException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
    	}catch (ValidationException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
		}
    }
    
    private List<Status> getAvailableStatuses(Issue issue) throws DaoException,
	ValidationException {
    	StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
    	List<Status> statuses = new ArrayList<Status>();
    	switch(issue.getStatus().getName()) {
    		case Constants.NEW:
    			statuses.add(issue.getStatus());
    			statuses.add(statusDAO.getByName(Constants.ASSIGNED));
    			break;
    		case Constants.ASSIGNED:
    			statuses.add(issue.getStatus());
    			statuses.add(statusDAO.getByName(Constants.IN_PROGRESS));
    			break;	
    		case Constants.CLOSED:
    			statuses.add(issue.getStatus());
    			statuses.add(statusDAO.getByName(Constants.REOPENED));
    			break;
    		case Constants.IN_PROGRESS:
    			statuses.add(issue.getStatus());
    			statuses.add(statusDAO.getByName(Constants.CLOSED));
    			statuses.add(statusDAO.getByName(Constants.RESOLVED));
    			break;
    		case Constants.RESOLVED:
    			statuses.add(statusDAO.getByName(Constants.CLOSED));
    			statuses.add(issue.getStatus());
    			statuses.add(statusDAO.getByName(Constants.REOPENED));
    			break;
    	
    	}
    	return statuses;
    }
    
    private List<Status> getAvailableStatusesForSubmitIssue() throws DaoException,
	ValidationException {
    	List<Status> statuses = new ArrayList<Status>();
    	StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
    	statuses.add(statusDAO.getByName(Constants.NEW));
    	statuses.add(statusDAO.getByName(Constants.ASSIGNED));
    	return statuses;
    }
}
