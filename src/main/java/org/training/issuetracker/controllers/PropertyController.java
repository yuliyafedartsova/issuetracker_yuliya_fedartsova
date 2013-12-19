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

public class PropertyController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public PropertyController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String property = request.getParameter(Constants.PROPERTY);
    	List<PropertyParameter> parametres = null;
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	switch(property) {
    	case Constants.STATUSES:
    		parametres = propertyDAO.getStatuses();
    		break;
    	case Constants.TYPES:
    		parametres = propertyDAO.getTypes();
    		break;
    	case Constants.PRIORITIES:
    		parametres = propertyDAO.getPriorities();
    		break;
    	case Constants.RESOLUTIONS:
    		parametres = propertyDAO.getResolutions();
    		break;
    	}
    	request.setAttribute(Constants.PARAMETRES, parametres);
    	jumpPage("/ParametersView", request, response);
    }
}
