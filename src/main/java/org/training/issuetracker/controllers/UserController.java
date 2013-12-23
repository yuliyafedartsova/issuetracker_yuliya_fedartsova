package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.factories.PropertyFactory;

import DAO.PropertyDAO;


public class UserController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public UserController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	List<PropertyParameter> roles = null;
    	try {
    	roles = propertyDAO.getRoles();
    	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}catch (Exception e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	request.setAttribute(Constants.ROLES, roles);
    	if(Constants.ADD.equals(action)) {
    		jumpPage(Constants.JUMP_ADD_USER, request, response);
    	} else {
    		jumpPage(Constants.JUMP_USER_DATA_UPDATE, request, response);
    	}
    }
}
