package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;


public class MainController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }

    public void init() {
		String realPath = getServletContext().getRealPath( "/") +
				 "WEB-INF\\classes\\";
		
		Constants.REAL_PATH = realPath;
		
		
	}

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	List<Issue> issues = null;
    	User user = (User)request.getSession().getAttribute(Constants.USER);
    	try {
    	if(user != null) {
    		issues = issuesDao.getNAssignedIssues(10, user);
    	} else {
    		issues = issuesDao.getNLastAddedIssues(10);
    	}
    	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	request.setAttribute(Constants.ISSUES, issues);
    	jumpPage(Constants.JUMP_MAIN, request, response);
    }
}
