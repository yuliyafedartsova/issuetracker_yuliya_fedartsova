package org.training.issuetracker.controllers.forms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;

/**
 * Servlet implementation class PropertyFormController
 */
public class PropertyFormController extends AbstractController {
	private static final long serialVersionUID = 1L;
    
    public PropertyFormController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	String property = request.getParameter(Constants.PROPERTY);
    	request.setAttribute(Constants.PROPERTY, property);
    	if(action.equals(Constants.ADD)) {
    		jumpPage("/pages/add_parameter.jsp", request, response);
    		return;
    	} else {
    		int id = Integer.parseInt(request.getParameter(Constants.ID));
    		try {
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
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}catch (Exception e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}
        	jumpPage(Pages.UPDATE_PARAMETER_PAGE, request, response);
        }
    }
 }

	
	


