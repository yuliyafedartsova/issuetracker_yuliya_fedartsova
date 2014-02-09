package org.training.issuetracker.controllers.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.factories.IssueFactory;



public class IssueReviewController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public IssueReviewController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter(Constants.ID));
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	try {
    		Issue issue = issuesDao.getIssueById(id); 	
    		request.setAttribute(Constants.ISSUE, issue);
    		jumpPage(Pages.REVIEW_ISSUE_PAGE, request, response);
    	}catch (DaoException e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    	}catch (Exception e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    	}
    }

}
