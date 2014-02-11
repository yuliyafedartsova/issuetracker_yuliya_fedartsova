package org.training.issuetracker.controllers.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ValidationManagers.UserValidator;


public class ChangePassword extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ChangePassword() {
        super();
    }
    
    protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	UserValidator validator = new UserValidator();
    	String password = request.getParameter(Constants.PASSWORD).trim();
        String password2 = request.getParameter(Constants.PASSWORD_CONFIRMATION).trim();
        String errorMessage = validator.validatePasswords(password, password2);
        try {
        	if(!errorMessage.isEmpty()) {
        		throw new ValidationException(errorMessage);
        	}
        	User user = (User)request.getSession().getAttribute(Constants.USER);
        	user.setPassword(password);
        	userDAO.updateUserData(user);
        	request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_CHANGE_PASSWORD);
        	jumpPage(Constants.MAIN, request, response);
        }catch (DaoException e) {
        	request.setAttribute(Constants.ERROR_MESSAGE, Constants.SOME_PROBLEMS);
        	jumpPage(Constants.MAIN, request, response);
        	return;
        }catch (ValidationException e) {
   			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
   			jumpPage(Pages.CHANGE_PASSWORD_PAGE, request, response);
     	} 
    }

	

}
