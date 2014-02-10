package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.RoleFactory;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.RolesDAO;



public class MainController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }

    public void init() {
		String realPath = getServletContext().getRealPath(Constants.DELIMITER);
	    Constants.PATH = realPath;
		System.out.println(Constants.PATH);
        ServletContext context = getServletContext();
		User guest = new User();
		guest.setRole(new Role(Constants.GUEST));
		context.setAttribute(Constants.USER, guest);
	
        RolesDAO rolesDao = RoleFactory.getClassFromFactory();
        try {
        Role role = rolesDao.getById(2);
        System.out.println(role.getName());
        }catch (DaoException e) {
    		System.out.println("Dao exception");
    		
		}
    
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	List<Issue> issues = null;
    	User user = (User)request.getSession().getAttribute(Constants.USER);
    	String sortingType = request.getParameter(Constants.SORTING);
    	sortingType = (sortingType == null) ? "default" : sortingType;
    	try {
    	if(user != null) { 
    		issues = issuesDao.getNAssignedIssues(10, user, sortingType);
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.EMPTY_MESSAGE, Constants.EMPTY_MESSAGE_FOR_USER);
    		}
    	} else {
    		
    		issues = issuesDao.getNLastAddedIssues(10, sortingType);
    		
    		
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.EMPTY_MESSAGE, Constants.EMPTY_MESSAGE_FOR_GUEST);
    		}
    	}
    	}catch (DaoException e) {
    		System.out.println("Dao exception");
    		jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}catch (Exception e) {
			System.out.println("exception");
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
	    }
    	
    	if(issues.size() != Constants.NULL) {
    		request.setAttribute(Constants.ISSUES, issues);
    	} else {
    		String message = (user == null) ? Constants.EMPTY_MESSAGE_FOR_GUEST : 
    			Constants.EMPTY_MESSAGE_FOR_USER;
    		jumpError(Pages.MAIN_PAGE, message, request, response);
    		return;
    	}
    	jumpPage(Pages.MAIN_PAGE, request, response);
    
    }
    
 }
