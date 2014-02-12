package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.utils.ValidationManagers.ParameterValidator;


public class PropertyFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
    
    public PropertyFormController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute(Constants.USER);
   	    if(user == null || user.getRole().getName() == Constants.GUEST) {
			jumpPage(Constants.MAIN, request, response);
			return;
		}
    	String action = request.getParameter(Constants.ACTION);
    	String property = request.getParameter(Constants.PROPERTY);
    	request.setAttribute(Constants.PROPERTY, property);
    	if(action.equals(Constants.ADD)) {
    		jumpPage(Pages.ADD_PARAMETER_PAGE, request, response);
    		return;
    	} else {
    		ParameterValidator validator = new ParameterValidator();
    		String idPar = request.getParameter(Constants.ID);
    		try {
    		validator.validateIdParameters(idPar);
    		int id = Integer.parseInt(idPar);
    		switch(property) {
        		case Constants.STATUS:
        			StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
        			Status statuse = statusDAO.getById(id);
        			request.setAttribute(Constants.PARAMETER, statuse);
        			break;
        		case Constants.TYPE:
        			TypesDAO typesDAO = TypeFactory.getClassFromFactory();
        			Type type = typesDAO.getById(id);
        			request.setAttribute(Constants.PARAMETER, type);
        			break;
        		case Constants.PRIORITY:
        			PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
        			Priority priority = priorityDAO.getById(id);
        			request.setAttribute(Constants.PARAMETER, priority);
        			break;
        		case Constants.RESOLUTION:
        			ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
        			Resolution resolution = resolutionDAO.getById(id);
        			request.setAttribute(Constants.PARAMETER, resolution);
        			break;
            	}
        	}catch (DaoException e) {
        		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
            	jumpPage(Constants.MAIN, request, response);
            	return;
    		}catch (ValidationException e) {
    			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
            	jumpPage(Constants.MAIN, request, response);
            	return;
    		}
        	jumpPage(Pages.UPDATE_PARAMETER_PAGE, request, response);
        }
    }
 }

	
	


