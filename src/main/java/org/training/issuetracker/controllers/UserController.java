package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.factories.PropertyFactory;


public class UserController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public UserController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	List<PropertyParameter> roles = propertyDAO.getRoles();
    	request.setAttribute(Constants.ROLES, roles);
    	if(Constants.ADD.equals(action)) {
    		jumpPage("/AddUserView", request, response);
    	} else {
    		jumpPage("/UserDataUpdateView", request, response);
    	}
    	
    	
    	
	}
}
