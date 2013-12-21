package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
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
    	try {
    		parametres = getParameters(property);
    	}catch (DaoException e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}catch (Exception e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	request.setAttribute(Constants.PARAMETRES, parametres);
    	jumpPage(Constants.JUMP_PARAMETERS, request, response);
    }
    
    private List<PropertyParameter> getParameters(String propertyName) throws DaoException {
    	List<PropertyParameter> parametres = null;
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	switch(propertyName) {
		case Constants.STATUS:
			parametres = propertyDAO.getStatuses();
			break;
		case Constants.TYPE:
			parametres = propertyDAO.getTypes();
			break;
		case Constants.PRIORITY:
			parametres = propertyDAO.getPriorities();
			break;
		case Constants.RESOLUTION:
			parametres = propertyDAO.getResolutions();
			break;
    	}
    	return parametres;
    }
}
