package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.UserFactory;


public class LoginController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public LoginController() {
        super();
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	String email = request.getParameter(Constants.EMAIL);
		String password = request.getParameter(Constants.PASSWORD);
		User user = null;
		try {
		user = userDAO.getUser(email, password);
		}catch (ValidationException e) {
			jumpError(Constants.MAIN, e.getMessage(), request, response);
			return;
		}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER, user);
		jumpPage(Constants.MAIN, request, response);
	}
}
