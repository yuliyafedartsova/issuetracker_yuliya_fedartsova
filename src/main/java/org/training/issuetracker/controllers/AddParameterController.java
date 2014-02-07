package org.training.issuetracker.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.IssueInstanceFactory;


public class AddParameterController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public AddParameterController() {
        super();
       
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
     }

}
