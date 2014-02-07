package org.training.issuetracker.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.enums.Properties;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;


public class AddUserController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddUserController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = UserFactory.getClassFromFactory();
        PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
        String firstName = request.getParameter(Constants.FIRST_NAME);
  	    String lastName = request.getParameter(Constants.LAST_NAME);
  	    String email = request.getParameter(Constants.EMAIL);
        int roleId = Integer.parseInt(request.getParameter(Constants.ROLE));
        String password = request.getParameter(Constants.PASSWORD);
        String password2 = request.getParameter(Constants.PASSWORD_CONFIRMATION);
        try{
     	   Role role = propertyDAO.getRoleById(roleId);
  	       User user = new User(firstName, lastName, email, role, password);
     	   userDAO.addUser(user);
        } catch (DaoException e) {
  		   
  	   } 
     
     }
	
}
