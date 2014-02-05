package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;

import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.DAO.PropertyDAO;

public class PropertyController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public PropertyController() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	String property = request.getParameter(Constants.PROPERTY);
    	request.setAttribute(Constants.PROPERTY, property);
    	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
    	if(action.equals("show")) {
    		List<Parameter> parametres = null;
        	try {
        		parametres = propertyDAO.getParametersByPropertyName(property);
        	}catch (DaoException e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}catch (Exception e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}
        	request.setAttribute(Constants.PARAMETRES, parametres);
        	jumpPage(Pages.PARAMETERS_PAGE, request, response);
        }else {
        	int id = Integer.parseInt(request.getParameter(Constants.ID));
        	Parameter parameter = null;
        	try {
        		parameter = propertyDAO.getParameter(property, id);
        		request.setAttribute(Constants.PARAMETER, parameter);
        		jumpPage(Pages.UPDATE_PARAMETER_PAGE, request, response);
        	}catch (DaoException e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}catch (Exception e) {
    			jumpPage(Pages.ERROR_PAGE, request, response);
    			return;
    		}
        }
    }
    	
 }
