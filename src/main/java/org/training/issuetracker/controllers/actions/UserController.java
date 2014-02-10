package org.training.issuetracker.controllers.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.RoleFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.utils.ValidationManagers.UserValidator;


public class UserController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public UserController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	UserDAO userDAO = UserFactory.getClassFromFactory();
        RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
        String firstName = request.getParameter(Constants.FIRST_NAME).trim();
  	    String lastName = request.getParameter(Constants.LAST_NAME).trim();
  	    String email = request.getParameter(Constants.EMAIL).trim();
        String roleIdPar = request.getParameter(Constants.ROLE);
        String password = request.getParameter(Constants.PASSWORD).trim();
        String password2 = request.getParameter(Constants.PASSWORD_CONFIRMATION).trim();
        UserValidator validator = new UserValidator();
        String errorMessage = validator.validatePasswords(password, password2);
        try{
        	if(!errorMessage.isEmpty()) {
        		throw new ValidationException(errorMessage);
        	}
        	validator.validateIdParameters(roleIdPar);
        	User user = null;
        	Role role = rolesDAO.getById(Integer.parseInt(roleIdPar));
        	switch(action) {
    		case Constants.ADD:
    			user = new User(firstName, lastName, email, role, password);
    	     	userDAO.addUser(user);
    	     	request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_USER);
    	   	    break;
    		case Constants.UPDATE:
    			int id = Integer.parseInt(request.getParameter(Constants.ID));
    			User originalUser = userDAO.getUserById(id); 
    			user = new User(id, firstName, lastName, email, role, originalUser.getPassword());
     		    userDAO.updateUserData(user);
    			User currentUser = (User)request.getSession().getAttribute(Constants.USER);
     		    if(id == currentUser.getId()) {
     		    	request.getSession().removeAttribute(Constants.USER);
         		    request.getSession().setAttribute(Constants.USER, user);
         		}
     		   request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_USER);
    		   break;
        	}
        	request.getRequestDispatcher("/main").forward(request, response);
        
        }catch (DaoException e) {
  		   
  	    }catch (ValidationException e) {
  			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
  			jumpPage("/user-form", request, response);
    	}  
    
    
    }
}
