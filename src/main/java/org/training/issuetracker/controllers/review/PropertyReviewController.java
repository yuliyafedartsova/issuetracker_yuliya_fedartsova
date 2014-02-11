package org.training.issuetracker.controllers.review;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;


public class PropertyReviewController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public PropertyReviewController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	String property = request.getParameter(Constants.PROPERTY);
    	request.setAttribute(Constants.PROPERTY, property);
    	try {
        	switch(property) {
        		case Constants.STATUS:
        			StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
        			List<Status> statuses = statusDAO.getAll();
        			request.setAttribute(Constants.PARAMETRES, statuses);
        			break;
        		case Constants.TYPE:
        			TypesDAO typesDAO = TypeFactory.getClassFromFactory();
        			List<Type> types = typesDAO.getAll();
        			request.setAttribute(Constants.PARAMETRES, types);
        			break;
        		case Constants.PRIORITY:
        			PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
        			List<Priority> priorities = priorityDAO.getAll();
        			request.setAttribute(Constants.PARAMETRES, priorities);
        			break;
        		case Constants.RESOLUTION:
        			ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
        			List<Resolution> resolutions = resolutionDAO.getAll();
        			request.setAttribute(Constants.PARAMETRES, resolutions);
        			break;
            	}
        	
        	}catch (DaoException e) {
        		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
            	jumpPage(Constants.MAIN, request, response);
            	return;
    		}catch (Exception e) {
    			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
            	jumpPage(Constants.MAIN, request, response);
            	return;
    		}
        	jumpPage(Pages.PARAMETERS_PAGE, request, response);
     }
}
