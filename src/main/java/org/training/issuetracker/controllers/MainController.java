package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.DAO.IssueDAO;




public class MainController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }
    
   
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	
    	
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	List<Issue> issues = null;
    	User user = (User)request.getSession().getAttribute(Constants.USER);
    	String sortingType = request.getParameter(Constants.SORTING);
    	sortingType = (sortingType == null) ? Constants.DEFAULT : sortingType;
    	try {
    	if(user == null) { 
    		issues = issuesDao.getNLastAddedIssues(Constants.ISSUES_QUANTITY, sortingType);
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.EMPTY_MESSAGE, Constants.EMPTY_MESSAGE_FOR_GUEST);
    		}
    	} else {
    		issues = issuesDao.getNAssignedIssues(Constants.ISSUES_QUANTITY, user, sortingType);
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.EMPTY_MESSAGE, Constants.EMPTY_MESSAGE_FOR_USER);
    		}
    	
    	}
    	}catch (DaoException e) {
    		e.printStackTrace();
    		jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}catch (ValidationException e) {
			e.printStackTrace();
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
	    }
    	if(issues.size() != Constants.NULL) {
    		request.setAttribute(Constants.ISSUES, issues);
    	}
   
    	jumpPage(Pages.MAIN_PAGE, request, response);
    } 
    
 }
