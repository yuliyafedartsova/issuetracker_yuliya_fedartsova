package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    	List<User> users;
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	users = userDAO.getUsers();
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = null;
		
		for(User us : users) {
			if(us.getEmail().equals(email) && us.getPassword().equals(password)) {
				user = us;
			}
		}
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		} 
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/MainController");
		rd.forward(request, response);
	}
}
