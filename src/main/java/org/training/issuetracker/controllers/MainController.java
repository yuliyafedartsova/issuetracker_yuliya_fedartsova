package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByAssignee;
import org.training.issuetracker.model.beans.comparators.IssueComparatorById;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByPriority;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByStatus;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByType;
import org.training.issuetracker.model.factories.IssueFactory;

import DAO.IssueDAO;


public class MainController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }

    public void init() {
		String realPath = getServletContext().getRealPath(Constants.DELIMITER) +
				 Constants.PATH_TO_FILES;
		Constants.PATH = realPath;
		System.out.println(Constants.PATH);
    
    
    
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	List<Issue> issues = null;
    	User user = (User)request.getSession().getAttribute(Constants.USER);
    	String sort = request.getParameter(Constants.SORTING);
    	try {
    	if(user != null) {
    		issues = issuesDao.getNAssignedIssues(10, user);
    		if(sort != null) {
    			sortIssues(sort, issues);
    		}
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.MESSAGE, Constants.EMPTY_MESSAGE_FOR_USER);
    		}
    	} else {
    		issues = issuesDao.getNLastAddedIssues(10);
    		if(sort != null) {
    			sortIssues(sort, issues);
    		}
    		if(issues.size() == Constants.NULL) {
    			request.setAttribute(Constants.MESSAGE, Constants.EMPTY_MESSAGE_FOR_GUEST);
    		}
    	}
    	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}catch (Exception e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	
    	if(issues.size() != Constants.NULL) {
    		request.setAttribute(Constants.ISSUES, issues);
    	}
    	jumpPage(Constants.JUMP_MAIN, request, response);
    }
    
    private void sortIssues(String sort, List<Issue> issues) {
    	switch(sort) {
    		case Constants.TYPE:
    			Collections.sort(issues, new IssueComparatorByType());
    			break;
    		case Constants.STATUS:
    			Collections.sort(issues, new IssueComparatorByStatus());
    			break;
    		case Constants.PRIORITY:
    			Collections.sort(issues, new IssueComparatorByPriority());
    			break;
    		case Constants.ID:
    			Collections.sort(issues, new IssueComparatorById());
    			break;
    		case Constants.ASSIGNEE:
    			Collections.sort(issues, new IssueComparatorByAssignee());
    			break;
    	}
   
    }
}
