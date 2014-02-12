package org.training.issuetracker.controllers.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
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
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.utils.ValidationManagers.ParameterValidator;

public class PropertyController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public PropertyController() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute(Constants.USER);
        if(user == null || user.getRole().getName() != Constants.ADMINISTRATOR) {
			jumpPage(Constants.MAIN, request, response);
			return;
		}
    	String action = request.getParameter(Constants.ACTION);
    	String property = request.getParameter(Constants.PROPERTY);
    	ParameterValidator validator = new ParameterValidator();
    	try {
    	switch(action) {
		case Constants.ADD:
			String parameter = request.getParameter(Constants.PARAMETER).trim();
			addParameter(property, parameter);
			request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_PARAMETER);
			break;
		case Constants.UPDATE:
			String idPar = request.getParameter(Constants.ID); 
			validator.validateIdParameters(idPar);
			String name = request.getParameter(Constants.PARAMETER);
			updateParameter(property, Integer.parseInt(idPar), name);
			request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_PARAMETER);
			break;
	    }
    	}catch (DaoException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
        	jumpPage(Constants.MAIN, request, response);
        	return;
  	    }catch (ValidationException e) {
  	    	request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
			jumpPage(Constants.PROPERTY_FORM_CONTROLLER, request, response);
  	        return;
  	    }  
    	jumpPage(Constants.MAIN, request, response);
    }
    
    private void addParameter(String property, String parameter) throws DaoException, ValidationException {
    	switch(property) {
		case Constants.TYPE:
			TypesDAO typesDAO = TypeFactory.getClassFromFactory();
			Type type = new Type(parameter);
			typesDAO.add(type);
			break;
		case Constants.PRIORITY:
			PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
			Priority priority = new Priority(parameter);
			priorityDAO.add(priority);
			break;
		case Constants.RESOLUTION:
			ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
			Resolution resolution = new Resolution(parameter);
			resolutionDAO.add(resolution);
			break;
    	}
    	
    }
    
    private void updateParameter(String property, int id, String name) throws DaoException, ValidationException {
    		switch(property) {
        		case Constants.STATUS:
        			StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
        			statusDAO.update(id, name);
        			break;
        		case Constants.TYPE:
        			TypesDAO typesDAO = TypeFactory.getClassFromFactory();
        			typesDAO.update(id, name);
        			break;
        		case Constants.PRIORITY:
        			PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
        			priorityDAO.update(id, name);
        			break;
        		case Constants.RESOLUTION:
        			ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
        			resolutionDAO.update(id, name);
        			break;
            }
    }
}