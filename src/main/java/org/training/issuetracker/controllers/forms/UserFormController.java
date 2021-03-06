package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.RoleFactory;


public class UserFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public UserFormController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute(Constants.USER);
   	    if(user == null || user.getRole().getName().equals(Constants.GUEST)) {
			jumpPage(Constants.MAIN, request, response);
			return;
		}
    	String action = request.getParameter(Constants.ACTION);
    	RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
    	List<Role> roles = null;
    	try {
    	roles = rolesDAO.getAll();
    	}catch (DaoException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
		}catch (Exception e) {
			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
		}
    	request.setAttribute(Constants.ROLES, roles);
    	if(Constants.ADD.equals(action)) {
    		if(user == null || !user.getRole().getName().equals(Constants.ADMINISTRATOR)) {
    			jumpPage(Constants.MAIN, request, response);
    			return;
    		}
    		jumpPage(Pages.ADD_USER_PAGE, request, response);
    	} else {
    		jumpPage(Pages.UPDATE_USER_DATA_PAGE, request, response);
    	}
    }
}
